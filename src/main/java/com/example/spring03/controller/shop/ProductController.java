package com.example.spring03.controller.shop;

import java.io.File;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
			String path ="C:\\CYJ_spring-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp9\\wtpwebapps\\yt_spring03\\WEB-INF\\views\\images\\"; 
//			String path ="C:\\CYJ_spring-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp9\\wtpwebapps\\yt_spring03\\src\\main\\webapp\\WEB-INF\\views\\images\\"; 

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
	
	@RequestMapping("edit/{product_id}")
	public ModelAndView edit(@PathVariable("product_id") int product_id, ModelAndView mav) {
		System.out.println("edit controller단에 들어오긴하냐고~~~~");
		System.out.println(product_id + " 이건뭐지ㅣㅣ");
		mav.setViewName("/shop/product_edit"); //이동할 페이지 이름
		mav.addObject("dto", productService.detailProduct(product_id)); //전달할 데이터 저장
		return mav; //페이지 이동
	}
	
	@RequestMapping("update.do")
	public String update(ProductDTO dto) {
		System.out.println(dto.getProduct_id() +" 야야야 번호 찍으라고~~~~");
		System.out.println(dto.toString());
		String filename="-";
		if(!dto.getFile1().isEmpty()) { // 첨부파일이 존재하면
			System.out.println("첨부파일 다른거 넣을거라고~~~~");
			filename= dto.getFile1().getOriginalFilename(); //dto에 저장된 서버상에 업로드된 파일명을 반환해서 filename 변수에 저장
		
			String path ="C:\\CYJ_spring-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp9\\wtpwebapps\\yt_spring03\\WEB-INF\\views\\images\\"; 
			try {
				new File(path).mkdir(); // 새로운 파일의 경로를 생성하고 해당 경로에 폴더를 만든다
				dto.getFile1().transferTo(new File(path+filename)); //transferTo(데이터 붙여넣기 될 파일)메서드를 사용하여 파일 복사
	
			}catch (Exception e) {
				e.printStackTrace();
			}
			dto.setPicture_url(filename); //picture_url에는 파일 이름이 들어간다. String 파입

		}else { //(상품) "새로운" 첨부파일이 없을떄 기존에 첨부한 파일 정보를 가져서와 dto2 변수에 넣는다
			System.out.println("기존첨부파일 불러오라고~~~~~");
			ProductDTO dto2 = productService.detailProduct(dto.getProduct_id()); //저장
			System.out.println(dto2 + " 불러오라고 기존첨부~~~~");
			dto.setPicture_url(dto2.getPicture_url());
		}
		
		productService.updateProduct(dto);
		return "redirect:/shop/product/list.do";
	}
	
	
	//상품정보를 삭제할때는 그 정보 안에 담긴 이미지도 같이 삭제해야한다.
	@RequestMapping("delete.do")
	public String delete(@RequestParam int product_id) {
		System.out.println(product_id+ " 번호가 들어오긴하냐~~~~~");
		String filename = productService.fileInfo(product_id); //상품 id를 매개변수로 삼아 서비스에 저장한후 filename 변수에 저장
		System.out.println(filename+ "delete() 컨트롤러단~~~~~~~~~~~");
		if(filename != null && !filename.equals("-")) { //첨부파일이 존재하면
			String path="C:\\CYJ_spring-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp9\\wtpwebapps\\yt_spring03\\WEB-INF\\views\\images\\";
			//상품에 담겨있는 이미지도 같이 삭제해야하기 때문에 이미지 경로를 지정
			
			File f = new File(path+filename);
			if(f.exists()) { //파일이 존재하면
				f.delete(); //파일삭제
			}
		}
		productService.deleteProduct(product_id); //레코드 삭제
		System.out.println("삭제 컨트롤러 되긴되냐~~~~");
		return "redirect:/shop/product/list.do";
	}
	
	
	
	
	
	
	

}
