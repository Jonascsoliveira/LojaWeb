package com.jonasoliveira.lojaweb.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jonasoliveira.lojaweb.services.DBService;

@Configuration
@Profile("test")
public class TesteConfig {
	
	@Autowired
	private DBService dbservice;

	@Bean
	public boolean instanciateDatabase() throws ParseException {
		dbservice.instanciateTestDatabase();
		return true;
	}
}
