package com.app.shivam.test;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.shivam.service.ProducerService;


public class TestProducer implements CommandLineRunner {
	
	
	private ProducerService service;

	public void run(String... args) throws Exception{
		service.send("hello"+new Date());
	}
	
	
}
