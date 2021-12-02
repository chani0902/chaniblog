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
	
	
//	1. 서블릿 호출 : 주소창에 url 입력 후 엔터 >> 요청 : 리퀘스트와 컨트롤러 맵핑된 메소드 동작 사이에 가로채는 메소드
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("preHandle");
		HttpSession session = request.getSession();
		
				
		String member_id = (String)session.getAttribute("member_id");
		String member_nickname = (String)session.getAttribute("member_nickname");
		
		logger.info("member_id : " + member_id);
		logger.info("member_nickname : {}", member_nickname);
		
		String sPath = request.getServletPath();
				
//		로그인을 하지 않은 상태에서 접근을 막을 sPath만 필터링
		if(sPath.equals("/b01_insert.do") || sPath.equals("/b01_selectOne.do")) {
//			controller에 있던 걸 interceptor로 가져온다..!
			if(member_id == null) {
				
				response.sendRedirect("mv_login.do");
			
				return false; // true를 주면 요청한 페이지로 그냥 가버리게 된다.
			}
		}
				
		return true;
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("postHandle");
		super.postHandle(request, response, handler, modelAndView);
//		로그아웃 후에 뭐 로그아웃이 잘 됐습니다 등의 안내문을 띄울 때도 사용 가능할듯..
		
		
	}
	
} // end class
