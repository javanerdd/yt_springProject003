package com.example.spring03.controller.upload;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name="uploadPath") //bean�� id�� uploadPath�� �±׸� ����
	String uploadPath;
	
}
