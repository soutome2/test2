package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example" }, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.example\\.demo\\.entity\\..*"),
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.example\\.demo\\.form\\..*"),
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.example\\.demo\\.repository\\..*"),
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.example\\.demo\\.service\\..*")
})
public class WedApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WedApiApplication.class, args);
	}

}
