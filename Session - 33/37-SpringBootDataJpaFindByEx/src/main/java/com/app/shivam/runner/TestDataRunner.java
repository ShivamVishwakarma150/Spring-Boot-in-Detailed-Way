package com.app.shivam.runner;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.shivam.entity.Book;
import com.app.shivam.repo.BookRepository;

@Component
public class TestDataRunner implements CommandLineRunner {
	@Autowired
	private BookRepository repo;
	
	public void run(String... args) throws Exception {
		repo.saveAll(
				Arrays.asList(
						new Book(101, "SBMS", "SHIVAM", 300.0, "Backend"),
						new Book(102, "CORE", "SHIVAM", 200.0, "Backend"),
						new Book(103, "ADV", "VIKASH", 400.0, "Backend"),
						new Book(104, "REACT", null, 500.0, "Frontend"),
						new Book(105, "HTML", "SHIVAN", 600.0, "Frontend"),
						new Book(106, "CSS", null, 300.0, "Frontend"),
						new Book(107, "ANGULAR", "VIKASH", 800.0, "Frontend"),
						new Book(108, "SQL", "SHIVAM", 200.0, "Database")
						)
				);
		
		//repo.findByBookCostLessThanEqual(500.0)
		//repo.findByAuthorIsNull()
		//repo.findByAuthorLike("V%")
		//repo.findByAuthorStartingWith("V")
		
		//repo.findByAuthorLike("%I")
		//repo.findByAuthorEndingWith("I")
		
		//repo.findByAuthorLike("%S%")
		//repo.findByAuthorContaining("S")
		
		//repo.findByAuthorLike("____")
		
		//repo.findByBookIdLessThanEqualOrBookTypeLike(105, "Front%")
		//repo.findByAuthorLikeOrderByBookNameDesc("A%")
		repo.findByBookIdBetween(101, 105)
		.forEach(System.out::println);
		
		Optional<Book> opt =  repo.findByBookIdIs(1050);
		if(opt.isPresent())
			System.out.println(opt.get());
		else 
			System.out.println("No Data Found");
		
	}

}
