package com.example.spring03.controller.chart;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.service.chart.GoogleChartService;

@Controller //json을 리턴하는 method가 있는 경으
@RequestMapping("/chart/*")
public class GoogleChartController {

	@Inject
	GoogleChartService googleChartService;
	
	@RequestMapping("chart1.do")
	public ModelAndView chart1() {
		System.out.println("차트만들준비 됏냐고~~~~~~~~");
		return new ModelAndView("chart/chart01");
	}
	
	@RequestMapping("chart2.do")
	public ModelAndView chart2() {
		System.out.println("차트2 들어오냐~1111111111111111");
		return new ModelAndView("chart/chart02");
	}
	
	@ResponseBody
	@RequestMapping("cart_money_list.do") //화면으로 넘어가는 것이 아닌 데이터를 리턴하는 경우
	public JSONObject cart_money_list() {
		System.out.println("차트2 들어오냐~222222222222");
		return googleChartService.getChartData();
	}
}
