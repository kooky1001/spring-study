package org.example.springjsp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {

	@LocalServerPort
	int port;

	@Test
	void index() throws Exception {

		TestRestTemplate restTemplate = new TestRestTemplate();

		ResponseEntity<String> entity = restTemplate.getForEntity("/click/test", String.class);

		String testContent = entity.toString();
		System.out.println("testContent = " + testContent);

	}
}
