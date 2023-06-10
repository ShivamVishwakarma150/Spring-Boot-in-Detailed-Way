package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.Model;

public interface ModelRepository 
	extends JpaRepository<Model, Integer> {

}
