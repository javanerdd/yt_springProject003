package com.example.spring03.model.member.dao;

import javax.servlet.http.HttpSession;

import com.example.spring03.model.member.dto.MemberDTO;

public interface MemberDAO {

	public String loginCheck(MemberDTO dto);
	
	public void logout(HttpSession session);
}
