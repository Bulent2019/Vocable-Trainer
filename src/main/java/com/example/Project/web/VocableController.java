package com.example.Project.web;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.Project.model.Vocable;
import com.example.Project.model.VocableRepository;
import com.example.Project.model.CategoryRepository;

@Controller
public class VocableController {

	@Autowired
	private VocableRepository repo;
	
	@Autowired
	private CategoryRepository crepo;
	
	
//	@RequestMapping(value="/login")
//	public String login() {
//		return "loginpage";
//	}
//	
	@RequestMapping(value = {"/", "/vocablelist"})
	public String vocableList(Model model) {
		model.addAttribute("vocables", repo.findAll());
		return "vocablelist";
	}
	
	// REST --> get all vocables
	@RequestMapping(value="/vocables/", method = RequestMethod.GET)
	public @ResponseBody List<Vocable> vocablelistRest() {
		return (List<Vocable>) repo.findAll();
	}
	
	// REST --> get vocables by id
	@RequestMapping(value="/vocables/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Vocable> findVocableRest(@PathVariable("id") Long vocableId) {
		return repo.findById(vocableId);
	}
	
	@RequestMapping(value = "/add")
	public String addVocable(Model model) {
		model.addAttribute("vocable", new Vocable());
		model.addAttribute("categories", crepo.findAll());
		return "addvocable";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Vocable vocable) {
		repo.save(vocable);
		return "redirect:vocablelist";
	}
	
//	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)	
	public String deletVocable(@PathVariable("id") Long id, Model model) {
		repo.deleteById(id);
		return "redirect:../vocablelist";
	}
	
	// REST --> get books by id and delete

	@RequestMapping(value="/api/del/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Vocable> delVocableRest(@PathVariable("id") Long vocableId) {
		repo.deleteById(vocableId);
		return (List<Vocable>) repo.findAll();
	}

	@GetMapping(value = "/edit/{id}")
	public String editVocable(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vocable", repo.findById(id));
		model.addAttribute("categories", crepo.findAll());
		return "editvocable";
	}
	
	// REST --> editing vocables
	@GetMapping(value="/editId/{id}")
	public @ResponseBody Optional<Vocable> editVocableRest(@PathVariable("id") Long vocableId, Model model) {
		repo.findById(vocableId);
		model.addAttribute("categories", crepo.findAll());
		return repo.findById(vocableId);
	}
	
	@GetMapping(value = "/saveEdit")
	public String saveEdit(Vocable vocable) {
		repo.save(vocable);
		return "redirect:vocablelist";
	}
}
