package com.example.spring03.service.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.spring03.model.member.dao.MemberDAO;
import com.example.spring03.model.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService{

	@Inject
	MemberDAO memberDao;
	
	@Override
	public String loginCheck(MemberDTO dto, HttpSession session) {
		//맞으면 이름이 넘어오고틀리면 null이 넘어옴
		
		String name=memberDao.loginCheck(dto);
		System.out.println(name+"  memberService~~~~ loginCheck");
		
		if(name != null) { //맞으면
			session.setAttribute("userid", dto.getUserid()); //세션변수 등록
			session.setAttribute("name", name);
		}
		
		return name;
	}
	
	@Override
	public void logout(HttpSession session) {
		session.invalidate(); //세션을 모두 초기화
	}
}
