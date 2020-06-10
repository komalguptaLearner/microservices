package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import brave.sampler.Sampler;

@EnableDiscoveryClient
@SpringBootApplication
@Profile("development")
@EnableFeignClients("com.example.demo")
public class OlShoppingOrderApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OlShoppingOrderApplication.class, args);
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
