package com.example.spring03.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.model.member.dto.MemberDTO;
import com.example.spring03.service.admin.AdminService;

@Controller("/admin/")
public class AdminController {
	
	@Inject
	AdminService adminService;
	
	@RequestMapping("login.do")
	public String login() {
		return "admin/login";
	}
	
	@RequestMapping("loginCheck.do")
	public ModelAndView loginCheck(MemberDTO dto, HttpSession session, ModelAndView mav) {
		String name = adminService.loginCheck(dto);
		if(name != null) { //로그인 성공
			session.setAttribute("admin_userid", dto.getUserid());
			session.setAttribute("admin_name", name);
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			mav.setViewName("admin/admin");
			mav.addObject("message","success");
		}else {
			mav.setViewName("admin/login");
			mav.addObject("message","error");
		}
		return mav;
		
	}

}
