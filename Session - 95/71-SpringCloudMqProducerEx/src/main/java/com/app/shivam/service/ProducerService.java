package com.app.shivam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerService {

	@Autowired
	private JmsTemplate template;
	
	@Value("${my.app.desti-name}")
	private String destination;
	
	public void send(String message) {
		template.send(destination,session-> 
				session.createTextMessage(message));
		System.out.println("MESSAGE SENT FROM PRODUCER "+message);
	}
	
}
