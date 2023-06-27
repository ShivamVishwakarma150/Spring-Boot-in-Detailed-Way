package com.app.shivam.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {
	
	@JmsListener(destination="${my.app.desti-name}")
	public void readMsg(String message) {
		System.out.println(message);
	}
}
