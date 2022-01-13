package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.example.demo.Util.LoginStatus;
import com.example.demo.Util.Util;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor{
	
	private LoginStatus ls;
	
	public BeforeActionInterceptor(LoginStatus ls) {
		this.ls = ls;
	}
	
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		if(req.getParameter("privateType") != null){
			if(ls.isLogined()) {
				Util.javaReplace(resp, "로그인 후 이용해 주세요", "/usr/member/showLogin");
				return false;
			}
		}
		
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
