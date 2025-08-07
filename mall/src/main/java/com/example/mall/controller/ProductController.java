package com.example.mall.controller;

import java.io.FilenameFilter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mall.dto.PageRequestDTO;
import com.example.mall.dto.PageResponseDTO;
import com.example.mall.dto.ProductDTO;
import com.example.mall.service.ProductService;
import com.example.mall.util.CustomFileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/products")
public class ProductController {
	private final CustomFileUtil fileUtil;
	private final ProductService productService;
	
	@PostMapping("/")
	public Map<String, Long> register(ProductDTO productDTO){
		log.info("register : " + productDTO);
		List<MultipartFile> files = productDTO.getFiles();
		List<String> uploadFileNames = fileUtil.saveFiles(files);
		productDTO.setUploadFileNames(uploadFileNames);
		log.info(uploadFileNames);
		
		Long pno = productService.register(productDTO);
		
		return Map.of("결과",pno);
	}
	
	@GetMapping("/view/{fileName}")
	public ResponseEntity<Resource> viewFileGet(@PathVariable("fileName") String fileName){
		return fileUtil.getFile(fileName);
	}
	
	@GetMapping("/list")
	public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO){
		log.info("list" + ".".repeat(30) + pageRequestDTO);
		return productService.getList(pageRequestDTO);
	}
	
	@GetMapping("{pno}")
	public ProductDTO read(@PathVariable("pno")Long pno) {
		return productService.get(pno);
	}
	
	@PutMapping("{pno}")
	public Map<String, String> modify(@PathVariable("pno") Long pno,ProductDTO productDTO){
		productDTO.setPno(pno);
		
		ProductDTO oldProductDTO = productService.get(pno);
		List<String> oldFileNames = oldProductDTO.getUploadFileNames();
		List<MultipartFile> files = productDTO.getFiles();
		List<String> currentUploadFileNames= fileUtil.saveFiles(files);
		List<String> uploadFileNames = productDTO.getUploadFileNames();
		
		if(currentUploadFileNames != null && currentUploadFileNames.size() > 0) {
			uploadFileNames.addAll(currentUploadFileNames);
		}
		
		productService.modify(productDTO);
		
		if(oldFileNames != null && oldFileNames.size() > 0) {
			List<String> removeFiles = oldFileNames
					.stream()
					.filter(fileName -> uploadFileNames.indexOf(fileName) == -1).collect(Collectors.toList());
			
			fileUtil.deleteFiles(removeFiles);
		}
		
		return Map.of("결과", "성공");
	}
}
