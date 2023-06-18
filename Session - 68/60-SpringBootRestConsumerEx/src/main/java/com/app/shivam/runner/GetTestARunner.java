package com.app.shivam.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@Component
public class GetTestARunner implements CommandLineRunner {

	public void run(String... args) throws Exception {
		//1. Create URL
		//String url = "http://localhost:9090/v1/api/book/showA";
		String url = "http://localhost:9090/v1/api/book/showA";
		
		//2. Create Request Headers
		//3. Create Request Body
		
		// 2+3 (Combined Them)

		//4. Create RestTemplate object
		RestTemplate template = new RestTemplate();
		
		//5. Make HTTP call and Get Response back
		ResponseEntity<String> response = template.getForEntity(url, String.class); //URL, ResponseFormat.class
		
		//6. print details
		System.out.println(response.getBody());
		System.out.println(response.getHeaders());
		System.out.println(response.getStatusCode().name());
		System.out.println(response.getStatusCode().value());
		
	}

}
