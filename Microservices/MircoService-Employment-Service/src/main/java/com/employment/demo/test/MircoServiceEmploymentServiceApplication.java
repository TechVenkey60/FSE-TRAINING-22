package com.employment.demo.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MircoServiceEmploymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MircoServiceEmploymentServiceApplication.class, args);
	}

}
