package com.example.spring03.controller.chart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController //json을 리턴하는 method가 있는 경으
@RequestMapping("/chart/*")
public class GoogleChartController {

	@RequestMapping("chart1.do")
	public ModelAndView chart1() {
		System.out.println("차트만들준비 됏냐고~~~~~~~~");
		return new ModelAndView("chart/chart01");
	}
}
