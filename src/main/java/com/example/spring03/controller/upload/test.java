//package com.example.spring02.controller.upload;
// 
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;
// 
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
// 
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.multipart.MultipartFile;
// 
//@Controller
//public class ImageUploadController {
// 
//    private static final Logger logger=
//            LoggerFactory.getLogger(ImageUploadController.class);
// 
//    @RequestMapping("imageUpload.do")
//    public void imageUpload(HttpServletRequest request,
//            HttpServletResponse response,
//            MultipartFile upload) throws Exception {
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html; charset=utf-8");
// 
//        //���ε��� ���� �̸�
//        String fileName=upload.getOriginalFilename();
// 
//        //������ ����Ʈ �迭�� ��ȯ
//        byte[] bytes=upload.getBytes();
// 
//        //�̹����� ���ε��� ���丮(���� ���丮�� ����)
//        String uploadPath=
//"D:\\work\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\spring02\\WEB-INF\\views\\images\\";
//        OutputStream out=new FileOutputStream(
//                new File(uploadPath+fileName));
// 
//        //������ ���ε�
//        out.write(bytes);
//        //Ŭ���̾�Ʈ�� ��� ǥ��
//        String callback=request.getParameter("CKEditorFuncNum");
// 
//        //����=>Ŭ���̾�Ʈ�� �ؽ�Ʈ ����(�ڹٽ�ũ��Ʈ ����)
//        PrintWriter printWriter=response.getWriter();
// 
//        String fileUrl=
//                    request.getContextPath()+"/images/"+fileName;
// 
//        printWriter.println(
//"<script>window.parent.CKEDITOR.tools.callFunction("
//+callback+",'"+fileUrl+"','�̹����� ���ε�Ǿ����ϴ�.')"
//+"</script>");
//        printWriter.flush();
//    }
// 
//}
// 
// 