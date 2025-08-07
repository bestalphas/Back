package com.example.mall;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mall.dto.PageRequestDTO;
import com.example.mall.dto.PageResponseDTO;
import com.example.mall.dto.ProductDTO;
import com.example.mall.service.ProductService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductServiceTest {
	@Autowired
	ProductService productService;
	
	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
		
		PageResponseDTO<ProductDTO> result = productService.getList(pageRequestDTO);
		
		result.getDtoList().forEach(dto -> log.info(dto));
	}
}
