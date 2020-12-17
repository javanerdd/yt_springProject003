package com.example.spring03.controller.upload;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name="uploadPath") //bean�� id�� uploadPath�� �±׸� ����, sevlet-conetext
	String uploadPath; //@Resource���� ���� ���ҽ� ���� uploadPath�� ������ �ȴ�.
	
	
	// method����� ������ ������ get����̴�. (�� get����϶��� �� �޼ҵ带 ��ģ��. post����϶��� �ƴ�)
	@RequestMapping(value="upload/uploadForm", method=RequestMethod.GET)
	public void uploadForm() {
		//upload / uploadForm.jsp �� ������   
	}
	
	// ���ε��ư->�ӽõ��丮�� ���ε�
	// ->���������� file�� ����
	// ->������ ���丮�� ���� 
	
	@RequestMapping(value="/upload/uploadForm", method=RequestMethod.POST)
	public ModelAndView uploadForm(MultipartFile file, ModelAndView mav ) throws Exception {
		logger.info("���� �̸�-- "+ file.getOriginalFilename());
		logger.info("����ũ��-- " +file.getSize());
		logger.info("����Ʈ Ÿ��-- "+file.getContentType());
		
		String savedName = file.getOriginalFilename();
		savedName = uploadFile(savedName,file.getBytes()); 
		//������ �ø��� ������ ����Ʈ�� �ø���, ����� �����̸� �����ͼ� ������̷� ��������
		
		mav.setViewName("upload/uploadResult");
		mav.addObject("savedName",savedName);
		
		return mav;
	}
	
	//���� �̸��� �ߺ����� �ʵ��� ó��
	private String uploadFile(String originalName, byte[] fileData) throws Exception{
        // uuid ���� (Universal Unique IDentifier, ���� ���� �ĺ���)
        // uuid�� ����� ������ ������ 2�� �ø��� �Ǹ� �̸��� �ߺ��Ǵµ� �׷��� 1���� ���� �� �� �ֱ� ������
        // uuid��� ������ �ĺ��ڸ� �־ (�������� �ڵ带 ����) �̸��� �ߺ����� �ʰ� �ؼ� �������� �ʰԲ� �Ϸ��� ���̴�.
        // uuid�� �����ؼ� �װ��� ������ �̸� �տ��� �ٿ��� �ߺ��� �����Ѵ�		

		
		UUID uid = UUID.randomUUID(); //��ġ�� �ʴ� ���ϸ��� ���� ����ũ�� �� ����
		
		String savedName = uid.toString()+ "_"+originalName; // ��������+�����̸� ����
		File target = new File(uploadPath,savedName); //������ ���� ��ü ����
		
		FileCopyUtils.copy(fileData, target); //������ ����(������,���)
		return savedName; //������ �����̸��� �ǵ��� �ش�.
		
		//�ӽ� ���丮�� ����� ���ε�� ������
		//������ ���丮�� ����
		//FileCopyUtils.copy(����Ʈ�迭, ���ϰ�ü)
	}
	
	
	
	
}
