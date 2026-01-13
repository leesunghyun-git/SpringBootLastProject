package com.sist.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootLastProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLastProjectApplication.class, args);
	}
/*	@Bean
	CommandLineRunner runner(GoogleGenAiChatModel model) {
		return args->{
			String response = model.call("홍대 인도커리 맛집 추천");
			System.out.println(response);
		};
	} */

}
