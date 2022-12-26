package com.demo.restapi;

import com.demo.restapi.configuration.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {
		System.out.println(LocalDateTime.now());
		SpringApplication.run(RestapiApplication.class, args);
	}

}
