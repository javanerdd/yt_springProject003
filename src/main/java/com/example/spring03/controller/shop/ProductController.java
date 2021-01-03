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

	@Inject // �������� ����(DI)
	ProductService productService;

	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		mav.addObject("list", productService.listProduct()); // ������ ����
		mav.setViewName("/shop/product_list"); // �̵��� ������ �̸�

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
		System.out.println("��~~~~���ӳİ�");
		if (!dto.getFile1().isEmpty()) { // ÷�������� �����ϸ�
			filename = dto.getFile1().getOriginalFilename();
			System.out.println(filename+" ~~~~~~~~~~~~���������ϳİ�~~~~~");
			String path ="C:\\CYJ_spring-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp9\\wtpwebapps\\yt_spring03\\WEB-INF\\views\\images\\"; 
//			String path ="C:\\CYJ_spring-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp9\\wtpwebapps\\yt_spring03\\src\\main\\webapp\\WEB-INF\\views\\images\\"; 

			//C:\CYJ_spring-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp9\wtpwebapps\yt_spring03\\src\\main\\webapp\\WEB-INF\\views\\images\\
			try {
				new File(path).mkdir(); // ���丮 ����
				//���ϰ�ü�� �����(�ӽõ��丮�� �����) ÷�������� �̵�
				//���丮�� ���������� new File(���+�����̸�)���� ������ �����ϰ�
				//(MultipartFile)file ��ü�� 
				//transferTo(������ �ٿ��ֱ� �� ����) �޼��带 ����Ͽ� ���Ϻ���
				dto.getFile1().transferTo(new File(path + filename));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setPicture_url(filename); //picture_url���� ���� �̸��� ����.
		System.out.println(filename);
		productService.insertProduct(dto);
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("edit/{product_id}")
	public ModelAndView edit(@PathVariable("product_id") int product_id, ModelAndView mav) {
		System.out.println("edit controller�ܿ� �������ϳİ�~~~~");
		System.out.println(product_id + " �̰ǹ����Ӥ�");
		mav.setViewName("/shop/product_edit"); //�̵��� ������ �̸�
		mav.addObject("dto", productService.detailProduct(product_id)); //������ ������ ����
		return mav; //������ �̵�
	}
	
	@RequestMapping("update.do")
	public String update(ProductDTO dto) {
		System.out.println(dto.getProduct_id() +" �߾߾� ��ȣ �������~~~~");
		System.out.println(dto.toString());
		String filename="-";
		if(!dto.getFile1().isEmpty()) { // ÷�������� �����ϸ�
			System.out.println("÷������ �ٸ��� �����Ŷ��~~~~");
			filename= dto.getFile1().getOriginalFilename(); //dto�� ����� ������ ���ε�� ���ϸ��� ��ȯ�ؼ� filename ������ ����
		
			String path ="C:\\CYJ_spring-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp9\\wtpwebapps\\yt_spring03\\WEB-INF\\views\\images\\"; 
			try {
				new File(path).mkdir(); // ���ο� ������ ��θ� �����ϰ� �ش� ��ο� ������ �����
				dto.getFile1().transferTo(new File(path+filename)); //transferTo(������ �ٿ��ֱ� �� ����)�޼��带 ����Ͽ� ���� ����
	
			}catch (Exception e) {
				e.printStackTrace();
			}
			dto.setPicture_url(filename); //picture_url���� ���� �̸��� ����. String ����

		}else { //(��ǰ) "���ο�" ÷�������� ������ ������ ÷���� ���� ������ �������� dto2 ������ �ִ´�
			System.out.println("����÷������ �ҷ������~~~~~");
			ProductDTO dto2 = productService.detailProduct(dto.getProduct_id()); //����
			System.out.println(dto2 + " �ҷ������ ����÷��~~~~");
			dto.setPicture_url(dto2.getPicture_url());
		}
		
		productService.updateProduct(dto);
		return "redirect:/shop/product/list.do";
	}
	
	
	//��ǰ������ �����Ҷ��� �� ���� �ȿ� ��� �̹����� ���� �����ؾ��Ѵ�.
	@RequestMapping("delete.do")
	public String delete(@RequestParam int product_id) {
		System.out.println(product_id+ " ��ȣ�� �������ϳ�~~~~~");
		String filename = productService.fileInfo(product_id); //��ǰ id�� �Ű������� ��� ���񽺿� �������� filename ������ ����
		System.out.println(filename+ "delete() ��Ʈ�ѷ���~~~~~~~~~~~");
		if(filename != null && !filename.equals("-")) { //÷�������� �����ϸ�
			String path="C:\\CYJ_spring-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp9\\wtpwebapps\\yt_spring03\\WEB-INF\\views\\images\\";
			//��ǰ�� ����ִ� �̹����� ���� �����ؾ��ϱ� ������ �̹��� ��θ� ����
			
			File f = new File(path+filename);
			if(f.exists()) { //������ �����ϸ�
				f.delete(); //���ϻ���
			}
		}
		productService.deleteProduct(product_id); //���ڵ� ����
		System.out.println("���� ��Ʈ�ѷ� �Ǳ�ǳ�~~~~");
		return "redirect:/shop/product/list.do";
	}
	
	
	
	
	
	
	

}
