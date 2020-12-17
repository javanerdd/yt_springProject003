package com.example.spring03.controller.shop;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.model.shop.service.ProductService;

@Controller
@RequestMapping("/shop/product/*")
public class ProductController {

	@Inject //�������� ����(DI)
	ProductService productService;
	
	@RequestMapping("list.do") 
	public ModelAndView list(ModelAndView mav) {
		mav.addObject("list", productService.listProduct()); //������ ����
		mav.setViewName("/shop/product_list"); //�̵��� ������ �̸�
		
		return mav;
	}
	
	@RequestMapping("detail/{product_id}")
	public ModelAndView detail(@PathVariable int product_id, ModelAndView mav) {
		
		mav.addObject("dto", productService.detailProduct(product_id));
		mav.setViewName("/shop/product_detail");
		
		return mav;
	}
}
