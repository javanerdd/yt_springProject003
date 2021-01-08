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
			model.addAttribute("message","������ �߼� �Ǿ����ϴ�.");
			System.out.println("���� �� �����İ�~~~~");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message","���� �߼� ����...");
		}
		System.out.println("�Ƴ��Ƴ��ȾƳ��� �� �ȵǳİ�`~~");
		return "/email/write";
	}
	
}
