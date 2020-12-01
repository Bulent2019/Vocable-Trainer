package com.example.Project.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<MyUser, Long> {
	MyUser findByUsername(String username);
}
