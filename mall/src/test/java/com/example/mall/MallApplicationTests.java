package com.example.mall;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import java.time.LocalDate;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.mall.domain.Todo;
import com.example.mall.dto.PageRequestDTO;
import com.example.mall.dto.PageResponseDTO;
import com.example.mall.dto.TodoDTO;
import com.example.mall.repository.TodoRepository;
import com.example.mall.service.TodoService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class MallApplicationTests {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private TodoService todoService;

	@Test
	void contextLoads() {
		log.info("-".repeat(20));
		log.info(todoRepository);
		log.info("-".repeat(20));
//		
//		for(int i = 1 ; i <= 100 ; i++) {
//			Todo todo = Todo.builder()
//					.title("제목..." + i)
//					.writer("Jin")
//					.dueDate(LocalDate.now())
//					.build();
//			
//			todoRepository.save(todo);
//		Long tno = 1L;
//		
//		Optional<Todo> result =	todoRepository.findById(tno);

//		Todo todo = result.orElseThrow();
//		todo.setTitle("수정한 데이터 99");
//		todo.setWriter("관리자");
//		todo.setDueDate(LocalDate.of(2024, 01, 01));
//		
//		todoRepository.save(todo);
//		todoRepository.deleteById(tno);
//		log.info(todoRepository.findAll());
//		
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
//		
//		Page<Todo> result = todoRepository.findAll(pageable);
//		
//		log.info(result.getTotalElements());
//		
//		result.getContent().stream().forEach(todo -> log.info(todo));
//
//		TodoDTO todoDTO = TodoDTO.builder().title("서비스테스트 중").writer("작성자").dueDate(LocalDate.of(2020, 10, 10)).build();
//
//		Long tno = todoService.register(todoDTO);
//		log.info("글 번호 : " + tno);
//		
//		Long tno = 2L;
//		
//		TodoDTO todoDTO = todoService.get(tno);
//		
//		log.info(todoDTO);
//		
//		
//		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//				.page(2)
//				.size(10)
//				.build();
//				
//		PageResponseDTO<TodoDTO> = todoService.list(pageRequestDTO);
//		log.info(Response);		
		
		
		
	}

}
