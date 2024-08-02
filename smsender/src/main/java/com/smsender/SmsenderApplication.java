package com.smsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.smsender.*")
public class SmsenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsenderApplication.class, args);
	}

}
