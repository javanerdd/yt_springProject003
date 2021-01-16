package com.example.spring03.model.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.spring03.model.board.dto.ReplyDTO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Override
	public List<ReplyDTO> list(Integer bno, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count(int bno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void create(ReplyDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ReplyDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer bno) {
		// TODO Auto-generated method stub
		
	}

	
}
