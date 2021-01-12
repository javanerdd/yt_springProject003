package com.example.spring03.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter{

	//Ŭ���̾�Ʈ�� ��û ��,�Ŀ� Ư�� �۾��� ó���ϰ��� �Ҷ� ����ϴ� ���(�����ϰ� ��Ʈ�ѷ� ���� �����̶�� �����ϸ� ��)
	//�뵵- �α��� ó��, pc��/ ������� �б� ó����
	private static final Logger logger = LoggerFactory.getLogger(SampleInterceptor.class);
	
	//��ó��
	@Override // preHandle�� ���� ��û ���� �����ϴ� �޼ҵ�
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception{
		logger.info("pre handle...");
		return true; //true�� ��� ����, false�� ����
	}
	
	//��ó��
	@Override //postHandle�� ���� ��û�� ���� ������ �����ϴ� �޼ҵ�
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception{
		logger.info("post handle...");
	}
	
}
	
