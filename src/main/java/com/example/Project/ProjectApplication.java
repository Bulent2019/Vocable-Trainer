package com.example.Project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Project.model.Vocable;
import com.example.Project.model.VocableRepository;
import com.example.Project.ProjectApplication;
import com.example.Project.model.Category;
import com.example.Project.model.CategoryRepository;
//import com.example.Project.model.User;
//import com.example.Project.model.UserRepository;


@SpringBootApplication
public class ProjectApplication {
	private static final Logger log = LoggerFactory.getLogger(ProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(VocableRepository repo, CategoryRepository crepo) {
		return (args) -> {
			log.info("save a couple of vocables");
//			repository.deleteAll();
//			crepo.deleteAll();
//			urepository.deleteAll();
			crepo.save(new Category("Body"));
			crepo.save(new Category("Food&Drinks"));
			crepo.save(new Category("Office"));
			
			repo.save(new Vocable("Beer", "Olut", "Olut"));
			repo.save(new Vocable("Printer", "Tulostin", "Tulostin"));
			repo.save(new Vocable("Finger", "Sormi", "Sormi"));
			repo.save(new Vocable("Food", "Ruoka", "Ruoka"));

//			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
//			urepository.save(user1);

			log.info("fetch all vocables");
			for (Vocable vocable : repo.findAll()) {
				log.info(vocable.toString());
			}
		};
	}

}
