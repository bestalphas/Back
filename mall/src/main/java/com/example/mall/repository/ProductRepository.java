package com.example.mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mall.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
