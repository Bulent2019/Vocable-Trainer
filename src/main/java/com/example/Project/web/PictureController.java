package com.example.Project.web;

import java.nio.file.Path;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Project.model.PictureRepository;
import com.example.Project.model.Picture;
import com.example.Project.model.UserRepository;
import com.example.Project.model.User;

@Controller
public class PictureController {
	
	@Autowired
	PictureRepository prepo;
	
	@Autowired
	UserRepository urepo;
	
	@Autowired
	private Path rootlocation;

//	@RequestMapping(value="/vocablelist")
//	public String userPics(Model model) throws Exception {
//		
//		return "vocablelist";
//	}
//	
//	@GetMapping("/images")
//	public String listUploadedFiles(Model model, Principal principal) throws Exception {
//
//		if (principal == null) {
//			return "redirect:/find";
//		}
//
//		User user = urepo.findByUsername(principal.getName());
//
//		List<String> stringss = user.getPictureList().stream()
//				.map(picture -> this.rootlocation.resolve(picture.getName()))
//				.map(path -> MvcUriComponentsBuilder
//						.fromMethodName(PictureController.class, "serveFile", path.getFileName().toString()).build()
//						.toString())				
//				.collect(Collectors.toList());
//		System.out.println("Kuvien tulostus");
//		model.addAttribute("files", stringss);
//		model.addAttribute("picture", prepo.findAll());
//		return "vocablelist";
//	}
	
		
}
