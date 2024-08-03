package com.smbackoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.smbackoffice.*")
public class SmbackofficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmbackofficeApplication.class, args);
		System.out.println("SMBACKOFFICE OK");
	}

}
