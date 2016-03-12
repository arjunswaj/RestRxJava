package com.asb.rxjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan("com.asb.rxjava")
public class RestRxJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestRxJavaApplication.class, args);
	}

}
