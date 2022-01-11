package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Util.KakaoAPI;
import com.example.demo.dto.ResultData;
import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;

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
		
		
		String userEmail = (String) userInfo.get("email");
		String userNickname = (String) userInfo.get("nickname");
		
		memberService.doJoin("kakao", userEmail, userNickname);
		Member loginedMember = memberService.getMember(userEmail, userNickname);
		
		if(userInfo.size() > 0) {
			session.setAttribute("accessToken", accessToken);
			session.setAttribute("loginedMember", loginedMember);
		}
		
		return "/usr/home/main";
	}
	
	@RequestMapping("/usr/member/doLogout")
	public String doLogout(HttpSession session) {
		
		String accessToken = (String) session.getAttribute("accessToken");
		KakaoAPI.kakaoLogout(accessToken);
		session.removeAttribute("loginedMember");
		session.removeAttribute("accessToken");
		
		return "/usr/home/main";
	}
}
