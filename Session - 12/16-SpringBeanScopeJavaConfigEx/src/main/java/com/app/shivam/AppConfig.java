package com.app.shivam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    
	// 1 Bean => 1 object => 1 Method
	@Bean
    @Scope("singleton")
    public Token t1() {
        return new Token();
    }
}