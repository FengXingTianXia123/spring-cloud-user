package com.user.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@SpringBootApplication(scanBasePackages = {"com.user"})
@EnableFeignClients(basePackages = {"com.user"})
@EnableCircuitBreaker
//@EnableRedisHttpSession
public class UserGatewayConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserGatewayConsumerApplication.class, args);
	}

	@Bean
	public OnlineUserListener onlineUserListener(){
		return new OnlineUserListener();
	}

//	@Bean
//	JedisConnectionFactory jedisConnectionFactory() {
//		JedisConnectionFactory factory = new JedisConnectionFactory();
//		return factory;
//	}


}
