package com.example.spring03.controller.chart;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.service.chart.GoogleChartService;

@Controller //json�� �����ϴ� method�� �ִ� ����
@RequestMapping("/chart/*")
public class GoogleChartController {

	@Inject
	GoogleChartService googleChartService;
	
	@RequestMapping("chart1.do")
	public ModelAndView chart1() {
		System.out.println("��Ʈ�����غ� �ѳİ�~~~~~~~~");
		return new ModelAndView("chart/chart01");
	}
	
	@RequestMapping("chart2.do")
	public ModelAndView chart2() {
		System.out.println("��Ʈ2 ������~1111111111111111");
		return new ModelAndView("chart/chart02");
	}
	
	@ResponseBody
	@RequestMapping("chart_money_list.do") //ȭ������ �Ѿ�� ���� �ƴ� �����͸� �����ϴ� ���
	public JSONObject cart_money_list() {
		System.out.println("��Ʈ2 ������~222222222222");
		return googleChartService.getChartData();
	}
}
