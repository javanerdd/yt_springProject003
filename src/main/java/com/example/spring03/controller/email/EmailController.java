package com.example.spring03.controller.email;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring03.model.email.EmailDTO;
import com.example.spring03.service.email.EmailService;

@Controller
@RequestMapping("/email/*")
public class EmailController {
	
	@Inject
	EmailService emailService;
	
	@RequestMapping("write.do")
	public String write() {
		return "email/write";
	}
	
	@RequestMapping("send.do")
	public String send(@ModelAttribute EmailDTO dto, Model model) {
		
		try {
			emailService.sendEmail(dto);
			model.addAttribute("message","메일이 발송 되었습니다.");
			System.out.println("메일 잘 가지냐고~~~~");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message","메일 발송 실패...");
		}
		System.out.println("아나아나안아나ㅏ 왜 안되냐고`~~");
		return "/email/write";
	}
	
}
