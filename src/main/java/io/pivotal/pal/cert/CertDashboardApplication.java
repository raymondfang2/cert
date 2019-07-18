package io.pivotal.pal.cert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CertDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CertDashboardApplication.class, args);

	}

	@Bean
	public RestOperations restOperations() {
		return new RestTemplate();
	}

}
