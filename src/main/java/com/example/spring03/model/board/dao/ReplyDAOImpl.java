package com.example.spring03.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring03.model.board.dto.ReplyDTO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<ReplyDTO> list(Integer bno, int start, int end) {
		
		//변수가 3개라 HashMap에 감싸서 전달
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("bno", bno);
		
		return sqlSession.selectList("reply.listReply",map);
	}

	@Override
	public int count(int bno) {
		return sqlSession.selectOne("reply.count",bno);
	}

	@Override
	public void create(ReplyDTO dto) {
		sqlSession.insert("reply.insertReply",dto);
	}

	@Override
	public void update(ReplyDTO dto) {
		
	}

	@Override
	public void delete(Integer bno) {
		
	}

	
}
