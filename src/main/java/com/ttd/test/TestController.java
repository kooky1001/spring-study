package com.ttd.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttd.domain.User;
import com.ttd.domain.UserRepository;

@RestController
public class TestController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/test")
	public User hello() {
		User user = new User();
		user.setUserId("gest");
		user.setName("구에스트");
		user.setEmail("email@gest.com");
		user.setPassword("gest");
		return userRepository.save(user);
	}
	
	@GetMapping("/test2")
	public String hello2() {
		return "hello2~";
	}
}
