package com.app.shivam.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shivam.entity.Book;
import com.app.shivam.repo.BookRepository;

@RestController
@RequestMapping("/book")
public class BookRestController {

	@Autowired
	private BookRepository repo;
	
	@PostMapping("/create")
	public String createBook(@RequestBody Book book) {
		repo.save(book);
		return "BOOK CREATED";
	}
}
