package com.UserAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAccountApplication.class, args);
		System.out.println("running UserAccount");


		
	}

}
