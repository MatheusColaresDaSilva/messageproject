package com.smconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.smconsumer.*")
@EnableFeignClients
public class SmconsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmconsumerApplication.class, args);
		System.out.println("SMCONSUMER OK");

	}

}
