package com.app.shivam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	List<Movie> findByGener(String gener);
}

