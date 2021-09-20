package com.gowtham.ecommerce.dto;

import java.util.Set;

import com.gowtham.ecommerce.entity.Address;
import com.gowtham.ecommerce.entity.Customer;
import com.gowtham.ecommerce.entity.Order;
import com.gowtham.ecommerce.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {
	
	private Customer customer;
	private Address shippingAddress;
	private Address BillingAddress;
	private Order order;
	private Set<OrderItem> orderItems;
	
	

}
