package com.example.spring03.model.shop.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring03.model.shop.dao.CartDAO;
import com.example.spring03.model.shop.dto.CartDTO;

@Service
public class CartServiceImpl implements CartService{

	@Inject
	CartDAO cartDao;

	@Override
	public List<CartDTO> cartMoney() {
		return null;
	}

	@Override
	public void insert(CartDTO dto) {
		cartDao.insert(dto);
	}

	@Override
	public List<CartDTO> listCart(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int cart_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(String userid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(int cart_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int sumMoney(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countCart(String userid, int product_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(CartDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyCart(CartDTO dto) {
		// TODO Auto-generated method stub
		
	}
}
