package com.example.Project.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Project.model.CategoryRepository;
import com.example.Project.model.Picture;
import com.example.Project.model.PictureRepository;
import com.example.Project.model.Vocable;
import com.example.Project.model.VocableRepository;
import com.example.Project.model.SignUpForm;
import com.example.Project.model.User;
import com.example.Project.model.UserRepository;

@Controller
public class VocableController {

	@Autowired
	private VocableRepository repo;
	
	@Autowired
	private CategoryRepository crepo;
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private PictureRepository prepo;
	
	@Autowired
	private Path rootLocation;

	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/signup")
    public String addStudent(Model model){
    	model.addAttribute("signupform", new SignUpForm());
        return "signup";
    }	
    
    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String save(@Validated @ModelAttribute("signupform") SignUpForm signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	if (urepo.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
		    		urepo.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    }    
	
	@RequestMapping(value = {"/", "/vocablelist"})
	public String vocableList(Model model) {
		model.addAttribute("vocables", repo.findAll());
		return "vocablelist";
	}
	
	@RequestMapping(value = "/add")
	public String addVocable(Model model) {
		model.addAttribute("vocable", new Vocable());
		model.addAttribute("categories", crepo.findAll());
		return "addvocable";
	}

	@GetMapping(value = "/edit/{id}")
	public String editVocable(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vocable", repo.findById(id));
		model.addAttribute("categories", crepo.findAll());
		return "editvocable";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Vocable vocable,@ModelAttribute (name ="image") Picture image, RedirectAttributes ra, 
			@RequestParam("fileImage") MultipartFile multipartFile, Model model) throws IOException {
		
		String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
		image.setName(fileName);
		Picture savedImage = prepo.save(image);  // to get the id for saving the id
		String uploadDir = "./images/" + savedImage.getPictureId();
		Path uploadPath = Paths.get(uploadDir);
		
		if(!Files.exists(uploadPath)) {		// check if file exists or needs to be build
			Files.createDirectories(uploadPath);
		}
		
		try(InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("File not saved: " + fileName);
		}
	
//		ra.addFlashAttribute("message", "The image has been saved successfully.");		
		
		repo.save(vocable);
		return "redirect:vocablelist";
	}
	
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws MalformedURLException {

		Path file = this.rootLocation.resolve(filename);
		Resource resource = new UrlResource(file.toUri());

		return ResponseEntity
				.ok()
				.body(resource);	
	}
	
//	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)	
	public String deletVocable(@PathVariable("id") Long id, Model model) {
		repo.deleteById(id);
		return "redirect:../vocablelist";
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
	
	// REST --> get vocables by id and delete

	@RequestMapping(value="/api/del/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Vocable> delVocableRest(@PathVariable("id") Long vocableId) {
		repo.deleteById(vocableId);
		return (List<Vocable>) repo.findAll();
	}
	
	// REST --> editing vocables
	@GetMapping(value="/editId/{id}")
	public @ResponseBody Optional<Vocable> editVocableRest(@PathVariable("id") Long vocableId, Model model) {
		repo.findById(vocableId);
		model.addAttribute("categories", crepo.findAll());
		return repo.findById(vocableId);
	}
	
}
