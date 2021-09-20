package com.gowtham.ecommerce.service;

import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.gowtham.ecommerce.dao.CustomerRepository;
import com.gowtham.ecommerce.dto.Purchase;
import com.gowtham.ecommerce.dto.PurchaseResponse;
import com.gowtham.ecommerce.entity.Address;
import com.gowtham.ecommerce.entity.Customer;
import com.gowtham.ecommerce.entity.Order;
import com.gowtham.ecommerce.entity.OrderItem;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	private CustomerRepository customerRepository;
	

	public CheckoutServiceImpl(CustomerRepository customerRepository)
	{
		this.customerRepository = customerRepository;
	}
	
	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		
		Order order = purchase.getOrder();
		
		String orderTrackingNumber = generateOrdertrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		Set<OrderItem> orderItems = purchase.getOrderItems();

		orderItems.forEach(item -> order.add(item));
		
		Address shippingAddress = purchase.getBillingAddress();
		order.setShippingAddress(shippingAddress);
		
		Address billingAddress = purchase.getBillingAddress();
		order.setBillingAddress(billingAddress);
		
		Customer customer = purchase.getCustomer();
		customer.add(order);
		
		customerRepository.save(customer);
		
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrdertrackingNumber() {
		return UUID.randomUUID().toString();
	}

}
