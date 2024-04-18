package org.example.springjsp.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorAdvice {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
	private ErrorResponse badRequestException(Exception ex) {
		log.warn("Exception = ", ex);
		String message = "잘못된 데이터로 요청하였습니다.";
		return new ErrorResponse(HttpStatus.BAD_REQUEST, message);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	private ErrorResponse illegalArgumentException(IllegalArgumentException ex) {
		log.warn("Exception = ", ex);
		return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
	}
}
