package org.example.springjsp.web.error;

import org.example.springjsp.domain.todolist.todo.Todo;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class ErrorAdviceTest {

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void objectMapperTest() throws JsonProcessingException {
		String test = objectMapper.writeValueAsString(Todo.builder().content("test").category(2L).build());
		System.out.println("test = " + test);
	}
}
