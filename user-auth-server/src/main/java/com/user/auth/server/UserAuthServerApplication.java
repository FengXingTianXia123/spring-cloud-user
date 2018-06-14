package com.user.auth.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.user"})
@MapperScan("com.user.auth.mapper")
@EnableFeignClients(basePackages = {"com.user"})
@EnableCircuitBreaker
public class UserAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthServerApplication.class, args);
	}
}
