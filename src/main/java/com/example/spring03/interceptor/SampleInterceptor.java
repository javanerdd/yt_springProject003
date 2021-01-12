package com.example.spring03.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter{

	//클라이언트의 요청 전,후에 특정 작업을 처리하고자 할때 사용하는 기능(간단하게 컨트롤러 같은 계층이라고 생각하면 됨)
	//용도- 로그인 처리, pc웹/ 모바일웹 분기 처리등
	private static final Logger logger = LoggerFactory.getLogger(SampleInterceptor.class);
	
	//선처리
	@Override // preHandle은 메인 요청 전에 경유하는 메소드
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception{
		logger.info("pre handle...");
		return true; //true면 계속 진행, false면 멈춤
	}
	
	//후처리
	@Override //postHandle은 메인 요청이 끝난 다음에 경유하는 메소드
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception{
		logger.info("post handle...");
	}
	
}
	
