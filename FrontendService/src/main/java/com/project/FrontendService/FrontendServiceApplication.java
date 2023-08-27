package com.project.FrontendService;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class FrontendServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontendServiceApplication.class, args);
		log.info("FRONTEND IS RUNNING...");
	}

}
