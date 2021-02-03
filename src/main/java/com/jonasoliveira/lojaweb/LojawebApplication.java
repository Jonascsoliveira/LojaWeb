package com.jonasoliveira.lojaweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jonasoliveira.lojaweb.services.S3Service;

@SpringBootApplication
public class LojawebApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(LojawebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
