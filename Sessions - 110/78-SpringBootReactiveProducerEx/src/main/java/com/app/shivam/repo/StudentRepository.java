package com.app.shivam.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.app.shivam.entity.Student;

public interface StudentRepository 
	extends ReactiveMongoRepository<Student, String>{

}
