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
		System.out.println(name +" ~~~~~controller�� service���� �Ѿ��");
		ModelAndView mav = new ModelAndView();
		// �����͸� ���� ��� �����͸� ���ÿ� �����ϱ� ���� ModelAndViewŸ���� ��ü�� ����
		
		if(name != null) { //�α��� ����
			
			System.out.println(name +"  ~~~~~~controller�� ���� �ƴ�");
			
			mav.setViewName("home");// ������������ �̵�
			System.out.println("1111111111111111111");
		}else { // �α��� ����
			System.out.println(name+ "~~~controller��  ���̰���???   ");
			mav.setViewName("member/login"); //�α���
			mav.addObject("message","error");
			System.out.println("2222222222222222222");
		}
		return mav;
	}
		
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session, ModelAndView mav) {
		memberService.logout(session); //���� �ʱ�ȭ �۾�
		
		mav.setViewName("member/login"); //�̵��� ������
		mav.addObject("massage","logout"); //��������
		return mav; //�������� �̵�
	}
	
	@RequestMapping("address.do")
	public String address() {
		return "member/join";
	}
	
	 //��û > controller > serviceimpl >  
	 //dao > serviceimpl > controller > jsp 
}
