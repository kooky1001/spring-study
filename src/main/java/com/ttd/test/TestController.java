package com.ttd.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttd.entity.User;
import com.ttd.service.UserRepository;

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
	public Map<String, String> hello2() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "testname");
		map.put("age", "28");
		return map;
	}
}
