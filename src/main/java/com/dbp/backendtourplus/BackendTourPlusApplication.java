package com.dbp.backendtourplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BackendTourPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendTourPlusApplication.class, args);
	}

}
