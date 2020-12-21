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
		//������ �̸��� �Ѿ����Ʋ���� null�� �Ѿ��
		
		String name=memberDao.loginCheck(dto);
		System.out.println(name +"~~~~~~~serviceImpl��111111");
		if(name != null) { //������
			System.out.println(name +"~~~~~~~serviceImpl��22222");

			session.setAttribute("userid", dto.getUserid()); //���Ǻ��� ���
			session.setAttribute("name", name);
		}
		System.out.println(name +"~~~~~~~serviceImpl��333333");

		return name;
	}
	
	
	
	
	
	@Override
	public void logout(HttpSession session) {
		session.invalidate(); //������ ��� �ʱ�ȭ
	}
}
