package com.example.mall.service;

import com.example.mall.dto.PageRequestDTO;
import com.example.mall.dto.PageResponseDTO;
import com.example.mall.dto.TodoDTO;

public interface TodoService {
	Long register(TodoDTO todoDTO);
	TodoDTO get(Long tno);
	
	void modify(TodoDTO todoDTO);
	void remove(Long tno);
	
	PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);
}
