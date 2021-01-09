package com.example.spring03.controller.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class); 
			
	@RequestMapping("imageUpload.do")
	public void imageUpload(HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestParam MultipartFile upload) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("ckeditor �����̹��� ���ӳİ�~~~~~~~~~~~~");
		String fileName = upload.getOriginalFilename(); // ���ε��� ���� �̸�
		byte[] bytes = upload.getBytes(); //������ ����Ʈ �迭�� ��ȯ

		//�̹����� ���ε��� ���丮(���� ���丮�� ����)		
		String uploadPath="C:\\CYJ_spring-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp9\\wtpwebapps\\yt_spring03\\WEB-INF\\views\\images\\";
		
		OutputStream out = new FileOutputStream(new File(uploadPath+fileName));
			
		out.write(bytes);//������ ���ε�
		String callback = request.getParameter("CKEditorFunNum"); //Ŭ���̾�Ʈ�� ��� ǥ��
		
		PrintWriter printWriter =response.getWriter();
		
		String fileUrl = request.getContextPath()+"/images/"+fileName;
		System.out.println(fileName+"  ���ϳ���~~~~~~~~~~");
		printWriter.println("<script>window.parent.CKEDITOR.tools.callFunction("
				+callback+",'"+fileUrl+"','�̹����� ���ε� �Ǿ����ϴ�.')"+"</script>");
				printWriter.flush(); //��Ʈ�� �ݱ�
		
	}

}
