package com.app.shivam;

import org.springframework.stereotype.Component;

@Component("pob")
public class AdvProcessImpl 
	implements IProcess {

	@Override
	public void getProcesCode() {
		System.out.println("P2");
	}

}
