package com.user.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.user"})
@EnableFeignClients(basePackages = {"com.user"})
@EnableCircuitBreaker
public class UserGatewayConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserGatewayConsumerApplication.class, args);
	}
}
