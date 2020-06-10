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
public class OlShoppingItemApplication {
	private final static Logger LOGGER = LoggerFactory.getLogger(OlShoppingItemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OlShoppingItemApplication.class, args);
	}
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
