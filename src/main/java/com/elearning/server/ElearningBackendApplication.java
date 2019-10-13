package com.elearning.server;

import com.elearning.server.property.HasilStorageProperties;
import com.elearning.server.property.MateriStorageProperties;
import com.elearning.server.property.SoalStorageProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties({
	MateriStorageProperties.class,
	HasilStorageProperties.class,
	SoalStorageProperties.class
})
@EnableJpaAuditing(auditorAwareRef = "securityAuditorAware")
public class ElearningBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElearningBackendApplication.class, args);
	}
}
