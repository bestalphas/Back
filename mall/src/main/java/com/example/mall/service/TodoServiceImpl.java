package com.example.mall.service;

import java.util.List;
import java.util.Optional;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mall.domain.Todo;
import com.example.mall.dto.PageRequestDTO;
import com.example.mall.dto.PageResponseDTO;
import com.example.mall.dto.TodoDTO;
import com.example.mall.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
	
	private final ModelMapper modelMapper;
	
	private final TodoRepository todoRepository;
	
	public Long register(TodoDTO todoDTO) {
		log.info("-".repeat(40));
		
		Todo todo = modelMapper.map(todoDTO, Todo.class);
		
		Todo savedTodo = todoRepository.save(todo);
		
		return savedTodo.getTno();
	}
	
	public TodoDTO get(Long tno) {
		Optional<Todo> result = todoRepository.findById(tno);
		
		Todo todo = result.orElseThrow();
		
		TodoDTO dto = modelMapper.map(todo, TodoDTO.class);
		
		return dto;
	}
	
	public void modify(TodoDTO todoDTO) {
		Optional<Todo> result = todoRepository.findById(todoDTO.getTno());
		
		Todo todo = result.orElseThrow();
		
		todo.setTitle(todoDTO.getTitle());
		todo.setWriter(todoDTO.getWriter());
		todo.setDueDate(todoDTO.getDueDate());
		
		todoRepository.save(todo);
	}
	
	public void remove(Long tno) {
		todoRepository.deleteById(tno);
	}
	
	public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO){
		Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(),Sort.by("tno").descending());
		/**페이지 번호 JPA페이지 0 사용자 1 
		 * 한페이지에 표시할 글의 개수
		 * 정렬 TNO를 기준으로 내림차순 정렬 **/
		
		/**페이징 되어있는 Todo 객체를 반환**/
		Page<Todo> result = todoRepository.findAll(pageable);
		
		
		/**조회된 Page<Todo> 객체에서 실제 Todo 엔티티 목록을 가져와서 스트림으로 처리
		 * 조회된 Todo 엔티티를 modelMapper 를 이용해서 TodoDTo 객체로 변환 List<TodoDTO>로 수집**/
		List<TodoDTO> dtoList = result.getContent().stream()
				.map(todo -> modelMapper.map(todo, TodoDTO.class))
				.collect(Collectors.toList());
		
		/**전체 게시물의 개수**/
		long totalCount = result.getTotalElements();
		
		PageResponseDTO<TodoDTO> responseDTO =
			PageResponseDTO.<TodoDTO>withAll()  //@Builder(builderMethodName = "withAll")
			.dtoList(dtoList)	//페이징 처리된 TodoDTO 목록
			.pageRequestDTO(pageRequestDTO) //원본 페이징 요청 정보 (페이지 번호, 크기...)
			.totalCount(totalCount) //전체 항목 
			.build(); //객체 생성완료
			
			
		return responseDTO;
	}
}
