package com.example.Project.model;

import org.springframework.data.repository.CrudRepository;

public interface MYUserRepository extends CrudRepository<MyUser, Long> {
	MyUser findByUsername(String username);
}
