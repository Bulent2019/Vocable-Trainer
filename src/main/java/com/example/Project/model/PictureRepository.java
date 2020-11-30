package com.example.Project.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PictureRepository extends CrudRepository<Picture, Long> {
	Picture findByName (String name);
	List <Picture> findByType(String type);
}
