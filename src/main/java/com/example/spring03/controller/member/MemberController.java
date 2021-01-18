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
		
		String name=memberService.loginCheck(dto, session); //맞으면 이름, 틀리면 null이 넘어옴
		System.out.println(name +" ~~~~~controller단 service에서 넘어옴");
		ModelAndView mav = new ModelAndView();
		// 데이터를 보낼 뷰와 데이터를 동시에 설정하기 위해 ModelAndView타입의 객체를 생성
		
		if(name != null) { //로그인 성공
			
			System.out.println(name +"  ~~~~~~controller단 널이 아님");
			
			mav.setViewName("home");// 시작페이지로 이동
			System.out.println("1111111111111111111");
		}else { // 로그인 실패
			System.out.println(name+ "~~~controller단  널이겟지???   ");
			mav.setViewName("member/login"); //로그인
			mav.addObject("message","error");
			System.out.println("2222222222222222222");
		}
		return mav;
	}
		
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session, ModelAndView mav) {
		memberService.logout(session); //세션 초기화 작업
		
		mav.setViewName("member/login"); //이동할 페이지
		mav.addObject("massage","logout"); //변수저장
		return mav; //페이지로 이동
	}
	
	@RequestMapping("address.do")
	public String address() {
		return "member/join";
	}
	
	 //요청 > controller > serviceimpl >  
	 //dao > serviceimpl > controller > jsp 
}
