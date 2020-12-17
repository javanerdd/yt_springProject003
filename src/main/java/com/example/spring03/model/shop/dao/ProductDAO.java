package com.example.spring03.model.shop.dao;

import java.util.List;

import com.example.spring03.model.shop.dto.ProductDTO;

public interface ProductDAO {
	
	List<ProductDTO> listProduct(); //목록

	ProductDTO detailProduct(int product_id); //상품상세
	
	void updateProduct(ProductDTO dto); //상품수정
	
	void deleteProduct(int product_id); //상품삭제
	
	void insertProduct(ProductDTO dto); //상품추가
	
	String fileInfo(int product_id); //파일정보
	

}
