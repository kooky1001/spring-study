package com.ttd.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/test")
	public String hello() {
		return "hello~";
	}
	
	@GetMapping("/test2")
	public String hello2() {
		return "hello2~";
	}
}
