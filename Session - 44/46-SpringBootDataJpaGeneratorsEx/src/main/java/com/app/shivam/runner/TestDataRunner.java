package com.app.shivam.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.shivam.entity.Product;
import com.app.shivam.repo.ProductRepository;

@Component
public final class TestDataRunner implements CommandLineRunner {

	@Autowired
	private ProductRepository repo;
	
	public void run(String... args) throws Exception {
		Product p1 = new Product();
		p1.setProdName("PEN");
		p1.setProdCost(200.0);
		
		p1 = repo.save(p1);
		System.out.println(p1.getProdId());
		
	}

}
