package com.app.shivam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudCartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudCartServiceApplication.class, args);
	}

}
