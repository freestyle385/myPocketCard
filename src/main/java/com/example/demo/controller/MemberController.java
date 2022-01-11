package com.example.demo.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Util.KakaoAPI;
import com.example.demo.dto.ResultData;
import com.example.demo.service.MemberService;

@Controller
public class MemberController {
	
	MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/usr/member/showLogin")
	public String showLogin() {
		return "/usr/member/login";
	}
	
	
	@RequestMapping("/usr/member/doLogin")
	public String doLogin(HttpSession session, String code) {
		
		// 1번 인증코드 요청 전달
		String accessToken = KakaoAPI.getAccessToken(code);
		// 2번 인증코드로 토큰 전달
		HashMap<String, Object> userInfo = KakaoAPI.getUserInfo(accessToken);
		
		String userEmail = null;
		String userNickname = null;
		
		if(userInfo.get("email") != null) {
			userEmail = (String) userInfo.get("email");
			userNickname = (String) userInfo.get("nickname");
			
			session.setAttribute("userId", userEmail);
			session.setAttribute("accessToken", accessToken);
			session.setAttribute("userNickname", userNickname);
		}
		
		ResultData<String> joinRd = memberService.doJoin("kakao", userEmail, userNickname);
		
		return "/usr/member/login";
	}
}
