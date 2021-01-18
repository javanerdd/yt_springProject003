package com.example.spring03.service.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.spring03.model.board.dao.ReplyDAO;
import com.example.spring03.model.board.dto.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Inject
	ReplyDAO replyDao;
	
	@Override
	public void create(ReplyDTO dto) {
		replyDao.create(dto);
	}

	@Override
	public List<ReplyDTO> list(Integer bno, int start, int end, HttpSession session) {
		return replyDao.list(bno, start, end);
	}

	@Override
	public ReplyDTO detail(Integer bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ReplyDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer bno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int count(Integer bno) {
		return replyDao.count(bno);
	}

}
