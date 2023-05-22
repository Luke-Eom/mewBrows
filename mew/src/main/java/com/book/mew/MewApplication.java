package com.book.mew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MewApplication {

	public static void main(String[] args) {
		SpringApplication.run(MewApplication.class, args);
	}

}
