package com.lizheng.play;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
//@EnableWebSecurity //启用web安全
public class PlaySpringBootApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PlaySpringBootApplication.class);
		app.run(args);
	}
}
