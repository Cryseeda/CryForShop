package com.gowtham.ecommerce.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.gowtham.ecommerce.entity.Product;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {
	//JPA creates a custom query based on the method name so be careful
	//select * from product where category_id=?
	Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
	
	Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);
}
