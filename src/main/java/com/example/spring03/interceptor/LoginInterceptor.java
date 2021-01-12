package com.example.spring03.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//HandlerInterceptorAdapter �߻�Ŭ���� ���
//preHandle(), postHandle() �������̵�
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	//�α����� �ϱ����� �α����� �Ǿ��ִ� �������� �˻��ϰ� �α����� �Ǿ�������
	//���� �׼����� �̵��ϰ�, �α����� �ȵǾ������� �α��� �������� �̵���Ų��.
	//���� �׼��� ����Ǳ� �� ����Ǵ� �޼ҵ�
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		System.out.println("�� �α��� ���ͼ��� �Ȱ����İ�~~~~~~~~!!");
		HttpSession session = request.getSession(); //���� ��ü ����
		
		if(session.getAttribute("userid") == null) { // ������ ������(�α��ε��� ���� ���¸�)
			response.sendRedirect(request.getContextPath()+"/member/login.do?message=nologin");
			return false; // ���� �׼����� ���� ����, ��� �������� ����
		}else {
			return true; //�������
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception{
		super.postHandle(request, response, handler, modelAndView);
	}

}
