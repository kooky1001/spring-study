package org.example.springjsp.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ErrorAdvice {

	@ApiResponse(responseCode = "400", content = @Content(schemaProperties = {
		@SchemaProperty(name = "errorCode", schema = @Schema(example = "400", type = "integer")),
		@SchemaProperty(name = "errorStatus", schema = @Schema(example = "BAD_REQUEST", type = "string")),
		@SchemaProperty(name = "errorMessage", schema = @Schema(example = "잘못된 데이터로 요청하였습니다.", type = "string"))
	}))
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class,
		IllegalArgumentException.class})
	private ErrorResponse badRequestException(Exception ex) {
		executeLog(ex);
		String message = "잘못된 데이터로 요청하였습니다.";

		if (ex instanceof IllegalArgumentException) {
			message = ex.getMessage();
		}

		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, message);
	}

	@ApiResponse(responseCode = "500", content = @Content(schemaProperties = {
		@SchemaProperty(name = "errorCode", schema = @Schema(example = "500", type = "integer")),
		@SchemaProperty(name = "errorStatus", schema = @Schema(example = "INTERNAL_SERVER_ERROR", type = "string")),
		@SchemaProperty(name = "errorMessage", schema = @Schema(example = "서버 내부에 오류가 발생하였습니다.", type = "string"))
	}))
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	private ErrorResponse internalServerError(RuntimeException ex) {
		executeLog(ex);
		log.info("Internal Server Error", ex);
		String message = "서버 내부에 오류가 발생하였습니다.";
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, message);
	}

	private void executeLog(Exception ex) {
		log.warn("Exception: {}", ex.getMessage());
	}
}
