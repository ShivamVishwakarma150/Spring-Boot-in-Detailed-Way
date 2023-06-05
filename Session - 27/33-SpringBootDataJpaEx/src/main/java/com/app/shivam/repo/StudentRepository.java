package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.Student;

public interface StudentRepository 
	extends JpaRepository<Student, Integer>
{

}
