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

import com.example.demo.datamodel.SalesOrder;
import com.example.demo.dto.ItemDTO;
import com.example.demo.dto.SalesOrderDTO;
import com.example.demo.repository.CustomerSORepository;
import com.example.demo.repository.ItemServiceProxy;
import com.example.demo.repository.SalesOrderRepository;

@Transactional
@Service
public class SalesOrderService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ModelMapper modelMapper;

	/*
	 * @Autowired private JavaMailSender mailSender;
	 */
	@Autowired
	private ItemServiceProxy proxy;

	@Autowired
	private SalesOrderRepository salesOrderRepository;

	@Autowired
	private CustomerSORepository customerSORepository;

	public List<SalesOrderDTO> all() {
		return salesOrderRepository.findAll().stream().map(c -> modelMapper.map(c, SalesOrderDTO.class))
				.collect(Collectors.toList());
	}

	public SalesOrderDTO save(SalesOrderDTO salesOrderDTO) {
		SalesOrder salesOrder=null;
		/* Check for availability of item */
		boolean isAvailable = false;
		 logger.info("{} checking Item Availability....");
		isAvailable = checkItemAvailability(salesOrderDTO);
		
		if(isAvailable==true) {
			logger.info("{} Item Available");
			salesOrder = salesOrderRepository.save(modelMapper.map(salesOrderDTO, SalesOrder.class));
		}else
			salesOrder = null;
		
		logger.info("{} Order placed!"+salesOrder);
		/*Customer customer = customerSORepository.getOne(salesOrder.getCustId());

		
		 * try { this.mailSender.send(mimeMessage -> {
		 * mimeMessage.setRecipient(Message.RecipientType.TO, new
		 * InternetAddress(customer.getCustEmail())); mimeMessage.setFrom(new
		 * InternetAddress("info@mymicroservice.com")); StringBuilder messageBodyBldr =
		 * new StringBuilder();
		 * messageBodyBldr.append("Dear ").append(customer.getCustLastName()).
		 * append(", ")
		 * .append(customer.getCustFirstName()).append(", thanks for your order. ")
		 * .append("Your order number is ").append(salesOrder.getId()).append(".");
		 * mimeMessage.setText(messageBodyBldr.toString());
		 * 
		 * }); } catch (MailException ex) { // simply log it and go on...
		 * System.err.println(ex.getMessage()); }
		 */
		return modelMapper.map(salesOrder, SalesOrderDTO.class);
	}

	public SalesOrderDTO get(long orderId) {
		Optional<SalesOrder> orderResult = salesOrderRepository.findById(orderId);
		if (!orderResult.isPresent()) {
			return null;
		}
		return modelMapper.map(orderResult.get(), SalesOrderDTO.class);
	}

	public void delete(long orderId) {
		salesOrderRepository.deleteById(orderId);
	}
	
	public boolean checkItemAvailability(SalesOrderDTO salesOrderDTO) {
		boolean isAvailable = false;
		int orderCount = salesOrderDTO.getOrderLineItems().size();
		for(int i=0;i<orderCount;i++) {
			String itemName = salesOrderDTO.getOrderLineItems().get(i).getItemName();
			
			ItemDTO itemList = proxy.get(itemName);
			if(itemList!=null) {
				isAvailable = true;
			}else {
				isAvailable = false;
				break;
			}
		}
		
		return isAvailable;
	}
}
