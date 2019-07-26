package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@SpringBootApplication
@EnableAutoConfiguration
public class UserserviceApplication {

	public static void main(String[] args) throws  Exception{
		SpringApplication.run(UserserviceApplication.class, args);

		URL lastfm = new URL("http://ws.audioscrobbler.com/2.0/");
		URLConnection connection = lastfm.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();
	}
}
