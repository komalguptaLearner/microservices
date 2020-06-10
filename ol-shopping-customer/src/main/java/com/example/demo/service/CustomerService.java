package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.datamodel.Customer;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.repository.CustomerRepository;

@Transactional
@Service
public class CustomerService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerRepository customerRepository;

	/*
	 * @Autowired private KafkaTemplate<String, String> kafkaTemplate;
	 */

	public List<CustomerDTO> all() {
		return customerRepository.findAll().stream().map(c -> modelMapper.map(c, CustomerDTO.class))
				.collect(Collectors.toList());
	}

	
	public CustomerDTO save(CustomerDTO customerDTO) {
		Customer customer = customerRepository.save(modelMapper.map(customerDTO, Customer.class));
		CustomerDTO result = modelMapper.map(customer, CustomerDTO.class);
		//kafkaTemplate.send("CustomerCreated", "CustomerCreated", new Gson().toJson(result));
		return result;
	}

	public CustomerDTO get(long customerId) {
		logger.info("customer id "+customerId);
		Optional<Customer> customerResult = customerRepository.findById(customerId);
		if(!customerResult.isPresent()){
			return null;
		}
		return modelMapper.map(customerResult.get(), CustomerDTO.class);
	}

	public void delete(long customerId) {
		logger.info("customer id "+customerId);
		customerRepository.deleteById(customerId);
	}
}
