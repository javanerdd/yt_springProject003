package com.example.spring03.controller.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.model.member.dto.MemberDTO;
import com.example.spring03.service.member.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Inject
	MemberService memberService;
	
	@RequestMapping("login.do")  //   /member/login.do
	public String login() {
		return "member/login";
	}
	
	@RequestMapping("login_check.do")
	public ModelAndView login_check(@ModelAttribute MemberDTO dto, HttpSession session ) {
		
		System.out.println(dto.getUserid());
		System.out.println(dto.getPasswd());
		
		String name=memberService.loginCheck(dto, session); //������ �̸�, Ʋ���� null�� �Ѿ��
		ModelAndView mav = new ModelAndView();
		
		if(name != null) { //�α��� ����
			mav.setViewName("home");// ������������ �̵�
			System.out.println(name+"~~~~~~~~~~~~~~~~~~~~");
		}else {// �α��� ����
			mav.setViewName("member/login"); //�α���
			mav.addObject("message","error");
		}
		return mav;
	}
	
	 //��û > controller > serviceimpl >  
	 //dao > serviceimpl > controller > jsp 
}
