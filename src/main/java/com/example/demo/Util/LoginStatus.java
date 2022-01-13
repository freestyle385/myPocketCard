package com.example.demo.Util;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import com.example.demo.vo.Member;
import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class LoginStatus {
	
	private Member loginedMember;
	
	public LoginStatus(HttpSession session) {
		loginedMember = (Member) session.getAttribute("loginedMember");
	}
	
	public boolean isLogined() {
		return loginedMember != null;
	}
	
}
