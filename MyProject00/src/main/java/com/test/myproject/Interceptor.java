package com.test.myproject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class Interceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);
	
	
//	1. ���� ȣ�� : �ּ�â�� url �Է� �� ���� >> ��û : ������Ʈ�� ��Ʈ�ѷ� ���ε� �޼ҵ� ���� ���̿� ����ä�� �޼ҵ�
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("preHandle");
		HttpSession session = request.getSession();
		
				
		String member_id = (String)session.getAttribute("member_id");
		String member_nickname = (String)session.getAttribute("member_nickname");
		
		logger.info("member_id : " + member_id);
		logger.info("member_nickname : {}", member_nickname);
		
		String sPath = request.getServletPath();
				
//		�α����� ���� ���� ���¿��� ������ ���� sPath�� ���͸�
		if(sPath.equals("/b01_insert.do") || sPath.equals("/b01_selectOne.do")) {
//			controller�� �ִ� �� interceptor�� �����´�..!
			if(member_id == null) {
				
				response.sendRedirect("mv_login.do");
			
				return false; // true�� �ָ� ��û�� �������� �׳� �������� �ȴ�.
			}
		}
				
		return true;
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("postHandle");
		super.postHandle(request, response, handler, modelAndView);
//		�α׾ƿ� �Ŀ� �� �α׾ƿ��� �� �ƽ��ϴ� ���� �ȳ����� ��� ���� ��� �����ҵ�..
		
		
	}
	
} // end class
