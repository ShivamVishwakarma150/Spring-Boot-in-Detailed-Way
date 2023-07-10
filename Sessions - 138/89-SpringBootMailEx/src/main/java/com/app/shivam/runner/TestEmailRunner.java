package com.app.shivam.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import com.app.shivam.service.AppMailSender;

@Component
public class TestEmailRunner implements CommandLineRunner {

	@Autowired
	private AppMailSender sender;
	
	public void run(String... args) throws Exception {

		UrlResource file2 = new UrlResource("https://stimg.cardekho.com/images/carexteriorimages/930x620/Jaguar/F-TYPE/7810/1675671305429/front-left-side-47.jpg");
		
		boolean sent = sender.sendEmail(
				"arvind.991954@gmail.com", //to
				new String[] { //cc
					"shivam.972176@gmail.com",
					"abhisekmishra1709@gmail.com"
				}, 
				new String[] {//bcc
						"abhisekmishra1709@gmail.com"
				},
				"God Of Cricket", //subject
				//text
				"<html> <body> <h1>Jaguar Car!!</h1> <p>Kudi kahdi baby pahla jaguar lelo, phir jinna marji pyar lelo</p> </body></html>", 
				new Resource[] { //files
						file2
				});

		if(sent)
			System.out.println("EMAIL IS SENT");
		else
			System.out.println("SENDING FAILED");
	}

}

