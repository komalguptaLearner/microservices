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

import com.example.demo.dto.ItemDTO;
import com.example.demo.service.ItemService;

@RequestMapping("/items")
@RestControllerAdvice
@RestController
public class ItemResource {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ItemService itemService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/")
	public List<ItemDTO> all() {
		logger.info("Get all item list");
		return itemService.all();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{itemName}")
	public ItemDTO get(@PathVariable String itemName) {
		logger.info("Get item by name");
		return itemService.get(itemName);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{itemName}")
	public ItemDTO put(@PathVariable String itemName,@RequestBody ItemDTO itemDTO) {
		itemDTO.setName(itemName);
		return itemService.save(itemDTO);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		itemService.delete(Long.parseLong(id));
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ItemDTO add(@RequestBody ItemDTO itemDTO) {
		return itemService.save(itemDTO);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Throwable ex) {
		// Add conditional logic to show differnt status on different exceptions
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
