package com.example.spring03.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.model.shop.dto.CartDTO;
import com.example.spring03.model.shop.service.CartService;

@Controller
@RequestMapping("/shop/cart/*") //�������� url mapping
public class CartController {
	
	@Inject
	CartService cartService;
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute CartDTO dto, HttpSession session ) {
		
		String userid = (String)session.getAttribute("userid");
		//�α��� ���θ� Ȯ�� �ϱ� ���� ���ǿ� ����� ���̵� Ȯ��
		
		if(userid == null) { //�α������� ���� �����̸� �α��� ȭ������ �̵�
			return "redirect:/member/login.do";
		}else {
			dto.setUserid(userid); 
			cartService.insert(dto); //��ٱ��� ���̺� ����
			return "redirect:/shop/cart/list.do"; // ��ٱ��� ������� �̵�
		}
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(HttpSession session, ModelAndView mav) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		String userid = (String)session.getAttribute("userid");
		
		if(userid != null) { //�α��� �ߴٸ�
			System.out.println("�α��������� ������~~~~~~~controller 1111111");
			List<CartDTO> list=cartService.listCart(userid); //��ٱ��� ���
			System.out.println("��ٱ��ϸ�� ���ٿ��� ���ߵ�~~~~~ controller222222");
			int sumMoney = cartService.sumMoney(userid); //�ݾ��հ�
			int fee = sumMoney >= 30000 ? 0 :2500;  //��۷� ���, 3���� �̻� ������
			
			map.put("sumMoney", sumMoney);
			map.put("fee",fee); //��۷�
			map.put("sum",fee+sumMoney); //��ü�ݾ�
			map.put("list",list); //��ٱ��� ���
			map.put("count", list.size()); //���ڵ� ����
			
			mav.setViewName("shop/cart_list"); //�̵��� ������ �̸�
			mav.addObject("map",map); //������ ����
			return mav; //ȭ���̵�
			
		}else { //�α��� ���� ���� ����
			return new ModelAndView("member/login", "", null);
		}
	}
	
	
	@RequestMapping("delete.do")
	public String delete(@RequestParam int cart_id) {
		
		cartService.delete(cart_id);
		
		return "redirect:/shop/cart/list.do";
		
	}
	
	@RequestMapping("deleteAll.do")
	public String deleteAll(HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		if(userid !=null) {
			cartService.deleteAll(userid);
		}
		return "redirect:/shop/cart/list.do";
	}
	
	@RequestMapping("update.do")
	public String update(int[] amount, int[] cart_id, HttpSession session) {
		
		String userid = (String)session.getAttribute("userid");
		for(int i = 0; i<cart_id.length; i++) {
			System.out.println(i+" ~~~~~1111111111111");
			if(amount[i] == 0) {
				System.out.println(amount[i]+"  ~~~~22222222222");

				cartService.delete(cart_id[i]);
				System.out.println(cart_id[i]+"  ~~~~~3333333333333");


			}else {
				CartDTO dto = new CartDTO();
				dto.setUserid(userid);
				dto.setCart_id(cart_id[i]);
				dto.setAmount(amount[i]);
				cartService.modifyCart(dto);
				System.out.println("5555555555");
			} 
		}
		return "redirect:/shop/cart/list.do";
	}
	
	
	
	
	
	
	
	
}
