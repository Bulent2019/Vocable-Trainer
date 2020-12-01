package com.example.Project;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Project.model.Category;
import com.example.Project.model.CategoryRepository;
import com.example.Project.model.MyUser;
import com.example.Project.model.MYUserRepository;
import com.example.Project.model.Vocable;
import com.example.Project.model.VocableRepository;


@SpringBootApplication
public class ProjectApplication {
	private static final Logger log = LoggerFactory.getLogger(ProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	
	@Bean
	Path path(){
		return Paths.get(System.getProperty("java.io.tmpdir"));
	}
	
	@Bean
	public CommandLineRunner vocableDemo(VocableRepository repo, CategoryRepository creposit, MYUserRepository ureposit) {
		return (args) -> {
			log.info("save a couple of vocables");
			repo.deleteAll();
			ureposit.deleteAll();
//			creposit.deleteAll();
			
			creposit.save(new Category("Body"));
			creposit.save(new Category("Food&Drinks"));
			creposit.save(new Category("Office"));
			creposit.save(new Category("Phrase"));
			creposit.save(new Category("Animals"));
			creposit.save(new Category("General"));
			
			repo.save(new Vocable("Beer", "Olut", creposit.findByName("Food&Drinks").get(0)));
			repo.save(new Vocable("Printer", "Tulostin", creposit.findByName("Office").get(0)));
			repo.save(new Vocable("Finger", "Sormi", creposit.findByName("Body").get(0)));
			repo.save(new Vocable("How are you?", "Mitä kuuluu?", creposit.findByName("Phrase").get(0)));
			repo.save(new Vocable("House", "Talo", creposit.findByName("General").get(0)));
			repo.save(new Vocable("Dog", "Koira", creposit.findByName("Animals").get(0)));
			repo.save(new Vocable("Car", "Auto", creposit.findByName("General").get(0)));
			repo.save(new Vocable("Head", "Pää", creposit.findByName("Body").get(0)));
			repo.save(new Vocable("Computer", "Tietokone", creposit.findByName("Office").get(0)));
			repo.save(new Vocable("I don't talk finnish.", "En puhu suomea.", creposit.findByName("Phrase").get(0)));
			repo.save(new Vocable("Sausage", "Makkara", creposit.findByName("Food&Drinks").get(0)));
			repo.save(new Vocable("Tree", "Puu", creposit.findByName("General").get(0)));
			repo.save(new Vocable("Teacher", "Opettaja", creposit.findByName("General").get(0)));

			MyUser one = new MyUser("userOne", "$2y$10$PeuqLT07hU.SpPwTDL07reNUFSl1MJYGTCQIvBmBHVHL9.Df05CWq", "ADMIN");
			ureposit.save(one);

			log.info("fetch all vocables");
			for (Vocable vocable : repo.findAll()) {
				log.info(vocable.toString());
			}
		};
	}
}
