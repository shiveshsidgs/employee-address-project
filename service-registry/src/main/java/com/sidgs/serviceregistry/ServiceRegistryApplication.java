package com.sidgs.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		//System.setProperty("spring.config.name", "application-eho");
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}

}
