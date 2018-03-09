package com.aws.alexa.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.aws.alexa.service")
public class AlexaSelfserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlexaSelfserviceApplication.class, args);
	}
}
