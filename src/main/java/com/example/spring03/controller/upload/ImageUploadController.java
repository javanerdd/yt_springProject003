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
		System.out.println("ckeditor 파일이미지 들어왓냐고~~~~~~~~~~~~");
		String fileName = upload.getOriginalFilename(); // 업로드한 파일 이름
		byte[] bytes = upload.getBytes(); //파일을 바이트 배열로 변환

		//이미지를 업로드할 디렉토리(배포 디렉토리로 설정)		
		String uploadPath="C:\\CYJ_spring-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp9\\wtpwebapps\\yt_spring03\\WEB-INF\\views\\images\\";
		
		OutputStream out = new FileOutputStream(new File(uploadPath+fileName));
			
		out.write(bytes);//서버로 업로드
		String callback = request.getParameter("CKEditorFunNum"); //클라이언트에 결과 표시
		
		PrintWriter printWriter =response.getWriter();
		
		String fileUrl = request.getContextPath()+"/images/"+fileName;
		System.out.println(fileName+"  파일네임~~~~~~~~~~");
		printWriter.println("<script>window.parent.CKEDITOR.tools.callFunction("
				+callback+",'"+fileUrl+"','이미지가 업로드 되었습니다.')"+"</script>");
				printWriter.flush(); //스트림 닫기
		
	}

}
