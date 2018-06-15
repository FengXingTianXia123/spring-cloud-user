package com.user.count;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.user"})
@MapperScan("com.user.count.mapper")
public class UserCountServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCountServerApplication.class, args);
	}
}
