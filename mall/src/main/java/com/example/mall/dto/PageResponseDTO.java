package com.example.mall.dto;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;

@Data
public class PageResponseDTO<E> {
	
	private List<E> dtoList; //실제 데이터 목록
	private List<Integer> pageNumList; // 화면에 표시될 페이지 번호 목록
	private PageRequestDTO pageRequestDTO; //요청정보(현재페이지,페이지 크기등)
	
	private boolean prev, next;  //이전/다음 페이지 존재 여부
	private int totalCount; //전체 항목 수
	private int prevPage, nextPage;//이전 /다음 페이지 번호
	private int totalPage; //전체 페이지 수
	private int current; //현재 페이지 번호(pageRequestDTO,getPage()와 동일)
	
	@Builder(builderMethodName = "withAll")
	
	public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long totalCount) {
		this.dtoList = dtoList;
		this.pageRequestDTO = pageRequestDTO;
		this.totalCount = (int) totalCount;
		
		int end = (int) (Math.ceil(pageRequestDTO.getPage()/10.0)) * 10;
		int start = end - 9; //페이지 번호 그룹의 시작
		
		int last = (int) (Math.ceil((totalCount)/(double) pageRequestDTO.getSize()));
		//실제 마지막 페이지
		end = Math.min(end, last);
		//마지막 페이지가 넘치지 않도록 조정
		this.prev = start > 1;
		//이전페이지가 존재 하는지?
		this.next = totalCount > (long) end * pageRequestDTO.getSize();
		//다음페이지가 존재 하는지?
		this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
		// 화면에 표시될 페이지 번호 목록
		if(prev) {
			this.prevPage = start - 1;
		}
		// 이전/다음 페이지 표시
		if(next) {
			this.nextPage = end + 1;
		}
		
		this.totalPage = last;
		this.current = pageRequestDTO.getPage();
		
	}
}
