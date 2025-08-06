package com.example.mall.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pno;
	
	private String pname;
	
	private int price;
	
	private String pdesc;
	
	private boolean delFlag; //삭제여부 소프트삭제 true 삭제 false 삭제 안됨
	
	@ElementCollection // ProductImage 객체를 리스트 형태로 저장하도록 자동설정. product 테이블과 별개로 productImage 정보를 저장하는 테이블을 설정
	@Builder.Default //기본적으로 ArrayList로 초기화 설정
	private List<ProductImage> imageList = new ArrayList<>();  // 상품에 첨부된 여러가지 이미지 정보를 담는 리스트
	
	public void addImage(ProductImage image) {
		
		image.setOrd(this.imageList.size());  //이미지 필드의 순서를 설정(ord)
		imageList.add(image);		//이미지를 이미지리스트에 추가
	}
	
	public void addImageString(String fileName) {
		
		ProductImage productImage = ProductImage.builder()
				.fileName(fileName)
				.build();
		
				addImage(productImage);
	}
	
	public void clearList() {
		this.imageList.clear();  //이미지 리스트에 담겨있는 모든 정보를 삭제 모든 이미지 한꺼번에 삭제(이미지 전체를 교체하거나 초기화)
	}
}
