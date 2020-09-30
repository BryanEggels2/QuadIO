package quad.trivia.api;

import quad.trivia.api.Repositories.Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import quad.trivia.api.Services.Service;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
		Repository.initialize(Service.Api);
	}

}
