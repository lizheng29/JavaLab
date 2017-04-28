package com.lizheng.play;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class PlaySpringBootApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PlaySpringBootApplication.class);
		app.run(args);
	}
}
