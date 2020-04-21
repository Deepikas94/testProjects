package com.deepika.testProjects;


import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
	
	
}
