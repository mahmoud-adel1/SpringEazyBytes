package com.eazybytes.eazyschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.eazybytes.eazyschool.proxy")
public class Example46Application {

	public static void main(String[] args) {
		SpringApplication.run(Example46Application.class, args);
	}

}
