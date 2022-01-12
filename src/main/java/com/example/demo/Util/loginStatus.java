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
public class loginStatus {
	
	private Member loginedMember;
	
	public loginStatus(HttpSession session) {
		loginedMember = (Member) session.getAttribute("loginedMember");
	}
	
	public boolean isLogined() {
		return loginedMember != null;
	}
	
}
