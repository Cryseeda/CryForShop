package com.gowtham.ecommerce.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gowtham.ecommerce.dto.Purchase;
import com.gowtham.ecommerce.dto.PurchaseResponse;
import com.gowtham.ecommerce.service.CheckoutService;

@CrossOrigin
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
	
	private CheckoutService checkoutservice;
	
	public CheckoutController(CheckoutService checkoutService) {
		this.checkoutservice = checkoutService;
	}
	
	@PostMapping("/purchase")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
		
		System.out.println("Check here");
		//System.out.println(purchase.getOrderItems());
	
		System.out.println(purchase.getOrder());
		
		  PurchaseResponse purchaseResponse = checkoutservice.placeOrder(purchase);
		  return purchaseResponse;
	}

}
