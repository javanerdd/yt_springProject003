package com.example.spring03.model.shop.dao;

import java.util.List;

import com.example.spring03.model.shop.dto.CartDTO;

public interface CartDAO {
	
	List<CartDTO> cartMoney();

	void insert(CartDTO dto);
	
	List<CartDTO> listCart(String userid); //��ٱ��� ���
	
	void delete(int cart_id);// ��ٱ��� ���� ����
	
	void deleteAll(String userid);//��ٱ��� ����
	
	void update(int cart_id);
	
	int sumMoney(String userid);//��ٱ��� �ݾ� �հ�
	
	int countCart(String userid, int product_id);// ��ٱ��� ��ǰ ����   
	
	void update(CartDTO dto);//��ٱ��� ����
	void modifyCart(CartDTO dto);
	
}
