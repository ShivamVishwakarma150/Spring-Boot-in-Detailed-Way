package com.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.app")
public class MyAppConfig {
	
	//1 method = 1 object 
	@Bean
	public MyRepository m1() {
		MyRepository m1=new MyRepository();
		m1.setCode("A");
		return m1;
	}
	
	@Bean
	public MyRepository m2() {
		MyRepository m2=new MyRepository();
		m2.setCode("B");
		return m2;
	}
}
