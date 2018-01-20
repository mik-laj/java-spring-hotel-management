package com.teamknp.hotel;

import io.springlets.format.config.EnableSpringletsEntityFormatWebSupport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSpringletsEntityFormatWebSupport
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}
}
