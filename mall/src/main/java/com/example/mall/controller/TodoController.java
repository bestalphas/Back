package com.example.mall.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mall.dto.PageRequestDTO;
import com.example.mall.dto.PageResponseDTO;
import com.example.mall.dto.TodoDTO;
import com.example.mall.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/todo")
public class TodoController {
	
	private final TodoService service;
	
	@GetMapping("/{tno}")
	public TodoDTO get(@PathVariable("tno") Long tno) {
		return service.get(tno);
	}
	
	@GetMapping("/list")
	public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
		log.info(pageRequestDTO);
		return service.list(pageRequestDTO);
	}
	
	@PostMapping("/")
	public Map<String, Long> register(@RequestBody TodoDTO todoDTO) {
		Long tno = service.register(todoDTO);
		return Map.of("tno", tno);
	}
	
	@PutMapping("/{tno}")
	public Map<String, String> modify(@PathVariable("tno") Long tno ,@RequestBody TodoDTO todoDTO){
		todoDTO.setTno(tno);
		service.modify(todoDTO);
		return Map.of("결과","성공");
	}
	
	@DeleteMapping("/{tno}")
	public Map<String, String> remove(@PathVariable("tno") Long tno){
		service.remove(tno);
		
		return Map.of("삭제 결과","삭제 성공");
	}
}
