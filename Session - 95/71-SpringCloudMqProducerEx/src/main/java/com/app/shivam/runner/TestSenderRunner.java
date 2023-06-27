package com.app.shivam.runner;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.shivam.service.ProducerService;

@Component
public class TestSenderRunner {

	@Autowired
	private ProducerService service;
	
//	@Override
//	public void run(String... args) throws Exception {
	
	@Scheduled(cron="*/10 * * * * *")
	public void sendMsgTest() throws Exception{
		service.send("HELLO "+new Date());
		
	}
	
	 
}
