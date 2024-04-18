package org.example.springjsp.web.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
	private int errorCode;
	private HttpStatus errorStatus;
	private String errorMessage;
}
