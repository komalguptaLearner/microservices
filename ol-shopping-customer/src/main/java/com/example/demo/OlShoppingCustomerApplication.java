package com.example.demo;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import brave.sampler.Sampler;


@SpringBootApplication
@EnableDiscoveryClient
@Profile("development")
public class OlShoppingCustomerApplication {
	private final static Logger LOGGER = LoggerFactory.getLogger(OlShoppingCustomerApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(OlShoppingCustomerApplication.class, args);
	}
	
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

}
