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

import com.example.demo.dto.SalesOrderDTO;
import com.example.demo.service.SalesOrderService;

@RequestMapping("/orders")
//@RestControllerAdvice
@RestController
public class SalesOrderResource {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SalesOrderService salesOrderService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/")
	public List<SalesOrderDTO> all() {
		return salesOrderService.all();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public SalesOrderDTO get(@PathVariable String id) {
		return salesOrderService.get(Long.parseLong(id));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{id}")
	public SalesOrderDTO put(@PathVariable String id, @RequestBody SalesOrderDTO salesOrderDTO) {
		salesOrderDTO.setId(Long.parseLong(id));
		return salesOrderService.save(salesOrderDTO);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		salesOrderService.delete(Long.parseLong(id));
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public SalesOrderDTO add(@RequestBody SalesOrderDTO salesOrderDTO) {
		logger.info("{} Placing order.........");
		return salesOrderService.save(salesOrderDTO);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Throwable ex) {
		logger.error("There was an error: ", ex);
		// Add conditional logic to show differnt status on different exceptions
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
