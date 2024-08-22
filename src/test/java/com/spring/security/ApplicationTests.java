package com.spring.security;

import com.spring.security.entity.User;
import com.spring.security.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Autowired
	JwtService jwtService;
	@Test
	void contextLoads() {
		User user = new User(4L,"mahesh@gmail.com","mahesh","12131321321");
		String token = jwtService.generateToken(user);
		System.out.println(token);
		Long Id = jwtService.getUserIdFromToken(token);
		System.out.println(Id);
	}

}
