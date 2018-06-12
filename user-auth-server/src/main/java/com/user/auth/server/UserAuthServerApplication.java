package com.user.auth.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.user"})
@MapperScan("com.user.info.mapper")
public class UserAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthServerApplication.class, args);
	}
}
