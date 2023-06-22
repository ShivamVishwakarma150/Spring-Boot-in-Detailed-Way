package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
