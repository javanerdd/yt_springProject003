package com.example.spring03.model.shop.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring03.model.shop.dto.CartDTO;

@Repository
public class CartDAOImpl implements CartDAO{

	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<CartDTO> cartMoney() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("cart.cart_money");
	}

	@Override
	public void insert(CartDTO dto) {
		sqlSession.insert("cart.insert",dto);
		
	}

	@Override
	public List<CartDTO> listCart(String userid) {
		System.out.println("listCart() DAO´Ü~~~~~~~~~");
		return sqlSession.selectList("cart.listCart",userid);
	}

	@Override
	public void delete(int cart_id) {
		sqlSession.delete("cart.delete",cart_id);
	}

	@Override
	public void deleteAll(String userid) {
		sqlSession.delete("cart.deleteAll",userid);
	}

	@Override
	public void update(int cart_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int sumMoney(String userid) {
		System.out.println("sumMoney() DAO´Ü ~~~~~~~~~");
		return sqlSession.selectOne("cart.sumMoney",userid);
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
		sqlSession.update("cart.modify",dto);
		
	}
	
	

}
