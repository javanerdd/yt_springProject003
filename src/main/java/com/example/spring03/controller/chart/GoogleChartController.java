package com.example.spring03.controller.chart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController //json�� �����ϴ� method�� �ִ� ����
@RequestMapping("/chart/*")
public class GoogleChartController {

	@RequestMapping("chart1.do")
	public ModelAndView chart1() {
		System.out.println("��Ʈ�����غ� �ѳİ�~~~~~~~~");
		return new ModelAndView("chart/chart01");
	}
}
