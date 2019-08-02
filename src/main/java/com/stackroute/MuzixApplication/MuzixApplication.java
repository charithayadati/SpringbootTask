package com.stackroute.MuzixApplication;

import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MuzixApplication {

	public static void main(String[] args) {

		SpringApplication.run(MuzixApplication.class, args);
		System.out.println("Spring Boot");
	}

}
