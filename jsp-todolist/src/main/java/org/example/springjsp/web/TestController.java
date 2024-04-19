package org.example.springjsp.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestController
public class TestController {

	@GetMapping("/test")
	public String get() {
		return "get";
	}

	@PostMapping("/test")
	public String post() {
		return "post";
	}

	@PutMapping("/test")
	public String put() {
		return "put";
	}

	@DeleteMapping("/test")
	public String delete() {
		return "delete";
	}
}
