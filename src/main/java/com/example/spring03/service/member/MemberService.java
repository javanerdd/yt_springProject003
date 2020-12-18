package com.example.spring03.service.member;

import javax.servlet.http.HttpSession;

import com.example.spring03.model.member.dto.MemberDTO;

public interface MemberService {
	
	public String loginCheck(MemberDTO dto, HttpSession session);

	public void logout(HttpSession session);
}
