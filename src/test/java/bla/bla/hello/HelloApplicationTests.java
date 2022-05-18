package bla.bla.hello;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HelloApplicationTests {
	private static final String RESOURCE_URL = "http://localhost:8080/api/person";

	@Test
	public void contextLoads() throws IOException {
		Process process = null;
		Person actualPerson;
		try {
			process = new ProcessExecutor().execute("gs-rest-service.jar");

			RestTemplate restTemplate = new RestTemplate();
			waitForStart(restTemplate);

			actualPerson = restTemplate.getForObject(RESOURCE_URL, Person.class);
			assertEquals(new Person("John", "Doe"), actualPerson);
		} finally {
			process.destroyForcibly();
		}
	}

	private void waitForStart(RestTemplate restTemplate) {
		while (true) {
			try {
				Thread.sleep(500);
				restTemplate.getForObject(RESOURCE_URL, String.class);
				return;
			} catch (Throwable throwable) {
				// ignoring errors
			}
		}
	}
}
