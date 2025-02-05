package com.example.demo.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.service.CustomerService;

@RequestMapping("/customers")
@RestControllerAdvice
@RestController
public class CustomerResource {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerService customerService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/")
	public List<CustomerDTO> all() {
		logger.info("Get list of customers");
		return customerService.all();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public CustomerDTO get(@PathVariable long id) {
		logger.info("Get by id customer details");
		return customerService.get(id);
	}

	@ResponseStatus(HttpStatus.OK)

	@PutMapping("/{id}")
	public CustomerDTO put(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
		logger.info("Update customer details");
		customerDTO.setId(Long.parseLong(id));
		return customerService.save(customerDTO);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		logger.info("Delete customer details");
		customerService.delete(Long.parseLong(id));
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO add(@RequestBody CustomerDTO customerDTO) {
		logger.info("Create customer record");
		return customerService.save(customerDTO);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Throwable ex) {
		logger.error("There was an error: ", ex);
		// Add conditional logic to show differnt status on different exceptions
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
