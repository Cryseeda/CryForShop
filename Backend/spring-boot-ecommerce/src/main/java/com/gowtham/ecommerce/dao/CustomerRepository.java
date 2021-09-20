package com.gowtham.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gowtham.ecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
