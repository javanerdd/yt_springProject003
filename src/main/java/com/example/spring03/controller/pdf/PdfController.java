package com.example.spring03.controller.pdf;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.service.pdf.PdfService;

@Controller
@RequestMapping("/pdf/*")
public class PdfController {
	
	@Inject
	PdfService pdfService;

	@RequestMapping("list.do")
	public ModelAndView list() throws Exception{
		String result=pdfService.createPdf(); //createPdf() 메소드에서 pdf파일이 생성되었는지 결과가 result에 담긴다.
		return new ModelAndView("pdf/result","message",result); //result 결과가 message로 pdf/result페이지로 전송된다.
	}
}
