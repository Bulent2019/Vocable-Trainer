package com.example.Project.model;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VocableRepository extends CrudRepository<Vocable, Long>{

		List<Vocable> findByWord(String word);
}
