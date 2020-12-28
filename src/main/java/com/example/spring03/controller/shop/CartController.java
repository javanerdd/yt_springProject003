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
@RequestMapping("/shop/cart/*") //공통적인 url mapping
public class CartController {
	
	@Inject
	CartService cartService;
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute CartDTO dto, HttpSession session ) {
		
		String userid = (String)session.getAttribute("userid");
		//로그인 여부를 확인 하기 위해 세션에 저장된 아이디 확인
		
		if(userid == null) { //로그인하지 않은 상태이면 로그인 화면으로 이동
			return "redirect:/member/login.do";
		}else {
			dto.setUserid(userid); 
			cartService.insert(dto); //장바구니 테이블에 저장
			return "redirect:/shop/cart/list.do"; // 장바구니 목록으로 이동
		}
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(HttpSession session, ModelAndView mav) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		String userid = (String)session.getAttribute("userid");
		
		if(userid != null) { //로그인 했다면
			System.out.println("로그인했으면 떠야지~~~~~~~controller 1111111");
			List<CartDTO> list=cartService.listCart(userid); //장바구니 목록
			System.out.println("장바구니목록 갔다왔음 떠야됨~~~~~ controller222222");
			int sumMoney = cartService.sumMoney(userid); //금액합계
			int fee = sumMoney >= 30000 ? 0 :2500;  //배송료 계산, 3만원 이상 무료배송
			
			map.put("sumMoney", sumMoney);
			map.put("fee",fee); //배송료
			map.put("sum",fee+sumMoney); //전체금액
			map.put("list",list); //장바구니 목록
			map.put("count", list.size()); //레코드 갯수
			
			mav.setViewName("shop/cart_list"); //이동할 페이지 이름
			mav.addObject("map",map); //데이터 저장
			return mav; //화면이동
			
		}else { //로그인 하지 않은 상태
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
