package com.example.mall;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mall.domain.Product;
import com.example.mall.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductRepositoryTest {
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void testInsert() {
		
		for(int i=0 ; i<10 ; i++) {
			Product product = Product.builder()
					.pname("상품 : " + i)
					.price(100*i)
					.pdesc("상품 설명: " + i)
					.build();
			product.addImageString(UUID.randomUUID().toString() + "_" + "IMAGE1.jpg");
			product.addImageString(UUID.randomUUID().toString() + "_" + "IMAGE2.jpg");
			
			productRepository.save(product);
			
			log.info("-".repeat(40));
		}
	}
}
