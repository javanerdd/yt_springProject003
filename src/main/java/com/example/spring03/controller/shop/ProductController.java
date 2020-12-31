package com.example.spring03.controller.shop;

import java.io.File;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.model.shop.dto.ProductDTO;
import com.example.spring03.model.shop.service.ProductService;

@Controller
@RequestMapping("/shop/product/*")
public class ProductController {

	@Inject // 의존관계 주입(DI)
	ProductService productService;

	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		mav.addObject("list", productService.listProduct()); // 데이터 저장
		mav.setViewName("/shop/product_list"); // 이동할 페이지 이름

		return mav;
	}

	@RequestMapping("detail/{product_id}")
	public ModelAndView detail(@PathVariable int product_id, ModelAndView mav) {

		mav.addObject("dto", productService.detailProduct(product_id));
		mav.setViewName("/shop/product_detail");

		return mav;
	}

	@RequestMapping("write.do")
	public String write() {
		return "shop/product_write";
	}

	@RequestMapping("insert.do")
	public String insert(ProductDTO dto) {
		String filename = "-";
		System.out.println("야~~~~들어왓냐고");
		if (!dto.getFile1().isEmpty()) { // 첨부파일이 존재하면
			filename = dto.getFile1().getOriginalFilename();
			System.out.println(filename+" ~~~~~~~~~~~~파일존재하냐고~~~~~");
			String path = "	C:\\CYJ_spring-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp9\\wtpwebapps\\yt_spring03\\src\\main\\webapp\\WEB-INF\\views\\images\\"; 
					
			//C:\CYJ_spring-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp9\wtpwebapps\yt_spring03\\src\\main\\webapp\\WEB-INF\\views\\images\\
			try {
				new File(path).mkdir(); // 디렉토리 생성
				//파일객체에 저장된(임시디렉토리에 저장된) 첨부파일을 이동
				//디렉토리를 생성했으니 new File(경로+파일이름)으로 파일을 생성하고
				//(MultipartFile)file 객체의 
				//transferTo(데이터 붙여넣기 될 파일) 메서드를 사용하여 파일복사
				dto.getFile1().transferTo(new File(path + filename));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setPicture_url(filename); //picture_url에는 파일 이름이 들어간다.
		System.out.println(filename);
		productService.insertProduct(dto);
		return "redirect:/shop/product/list.do";

	}

}
