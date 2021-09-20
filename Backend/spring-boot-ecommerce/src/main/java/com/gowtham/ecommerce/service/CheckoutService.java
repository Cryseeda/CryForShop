package com.gowtham.ecommerce.service;

import com.gowtham.ecommerce.dto.Purchase;
import com.gowtham.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
	
	PurchaseResponse placeOrder(Purchase purchase);

}
