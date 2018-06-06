package com.crp.qa.qaAuthorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class QaAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(QaAuthorizationApplication.class, args);
	}
}
