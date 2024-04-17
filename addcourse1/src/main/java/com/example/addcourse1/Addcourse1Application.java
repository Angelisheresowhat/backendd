package com.example.addcourse1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
public class Addcourse1Application {

	public static void main(String[] args) {
		SpringApplication.run(Addcourse1Application.class, args);
	}

}
