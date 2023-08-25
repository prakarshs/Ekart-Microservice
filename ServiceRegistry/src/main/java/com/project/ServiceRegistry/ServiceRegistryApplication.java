package com.project.ServiceRegistry;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@Log4j2
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
		log.info("SERVICE REGISTRY RUNNING...");
	}

}
