package com.app.shivam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringCloudMqProducerExApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudMqProducerExApplication.class, args);
	}

}
