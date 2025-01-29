package com.mitocode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//anotacion scanea otras anotaciones del proyecto propias de sb
public class MitoSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MitoSalesApplication.class, args);
	}

}
