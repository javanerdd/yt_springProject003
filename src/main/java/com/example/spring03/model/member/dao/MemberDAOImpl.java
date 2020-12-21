package com.example.spring03.model.member.dao;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring03.model.member.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Inject
	SqlSession sqlSession;
	
	@Override
	public String loginCheck(MemberDTO dto) {
		System.out.println(dto.toString());
		return sqlSession.selectOne("member.login_check",dto);
	}
	
	@Override
	public void logout(HttpSession session) {
		
	}
	
	
}
