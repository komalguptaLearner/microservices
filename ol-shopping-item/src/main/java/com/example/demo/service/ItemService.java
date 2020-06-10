package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.datamodel.Item;
import com.example.demo.dto.ItemDTO;
import com.example.demo.repository.ItemRepository;

@Transactional
@Service
public class ItemService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ItemRepository itemRepository;

	public List<ItemDTO> all() {
		return itemRepository.findAll().stream().map(item -> modelMapper.map(item, ItemDTO.class))
				.collect(Collectors.toList());
	}

	public ItemDTO save(ItemDTO itemDTO) {
		Item customer = itemRepository.save(modelMapper.map(itemDTO, Item.class));
		return modelMapper.map(customer, ItemDTO.class);
	}

	public ItemDTO get(String itemName) {
		logger.info("{} "+itemName);
		Item item = itemRepository.findByName(itemName);
		if(item!=null)
			return modelMapper.map(item, ItemDTO.class);
		else
			return null;
	}

	public void delete(long itemId) {
		itemRepository.deleteById(itemId);
	}
}
