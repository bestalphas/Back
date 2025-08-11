package com.example.mall.service;


import com.example.mall.dto.PageRequestDTO;
import com.example.mall.dto.PageResponseDTO;
import com.example.mall.dto.ProductDTO;

import jakarta.transaction.Transactional;


@Transactional
public interface ProductService {
	
	PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);
	Long register(ProductDTO productDTO);
	
	ProductDTO get(Long pno);
	
	void modify(ProductDTO productDTO);
	
	void remove(Long pno);
}
