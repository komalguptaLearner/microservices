package com.example.demo.repository;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.ItemDTO;

//@FeignClient(name="item-service", url="localhost:9022/items")
@FeignClient(name="item-service")
@RibbonClient(name="item-service")
public interface ItemServiceProxy {
	
	@GetMapping("/items/{itemName}")
	public ItemDTO get(@PathVariable("itemName") String itemName);

}
