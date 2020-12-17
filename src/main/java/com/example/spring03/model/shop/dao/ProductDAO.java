package com.example.spring03.model.shop.dao;

import java.util.List;

import com.example.spring03.model.shop.dto.ProductDTO;

public interface ProductDAO {
	
	List<ProductDTO> listProduct(); //���

	ProductDTO detailProduct(int product_id); //��ǰ��
	
	void updateProduct(ProductDTO dto); //��ǰ����
	
	void deleteProduct(int product_id); //��ǰ����
	
	void insertProduct(ProductDTO dto); //��ǰ�߰�
	
	String fileInfo(int product_id); //��������
	

}
