package com.user.info;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.user"})
@MapperScan("com.user.info.mapper")
public class UserInfoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserInfoServerApplication.class, args);
	}
}
