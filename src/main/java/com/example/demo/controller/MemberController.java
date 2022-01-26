package com.example.demo.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Util.KakaoAPI;
import com.example.demo.Util.Util;
import com.example.demo.dto.ResultData;
import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;

@Controller
public class MemberController {
	
	MemberService memberService;
	KakaoAPI kakaoAPI;
	
	public MemberController(MemberService memberService, KakaoAPI kakaoAPI) {
		this.memberService = memberService;
		this.kakaoAPI = kakaoAPI;
	}

	@RequestMapping("/usr/member/showLogin")
	public String showLogin() {
		return "/usr/member/login";
	}
	
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpSession session, String code) {
		
		// 1번 인증코드 요청 전달
		String accessToken = kakaoAPI.getAccessToken(code);
		// 2번 인증코드로 토큰 전달
		HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(accessToken);
		
		
		String userEmail = (String) userInfo.get("email");
		String userNickname = (String) userInfo.get("nickname");
		
		ResultData<Member> joinRd = memberService.doJoin("kakao", userEmail, userNickname);
		Member loginedMember = joinRd.getData();
		
		if(userInfo.size() > 0) {
			session.setAttribute("accessToken", accessToken);
			session.setAttribute("loginedMember", loginedMember);
		}
		
		return Util.jsReplace("", String.format("/usr/home/main"));
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpSession session) {
		
		String accessToken = (String) session.getAttribute("accessToken");
		kakaoAPI.kakaoLogout(accessToken);
		session.removeAttribute("loginedMember");
		session.removeAttribute("accessToken");
		
		return Util.jsReplace("", String.format("/usr/home/main"));
	}
	
	@RequestMapping("/usr/member/info")
	public String showInfo(HttpSession session, Model md) {
		
		Member loginedMember = (Member) session.getAttribute("loginedMember");
		Member memberInfo = memberService.getMember(loginedMember.getUserEmail());
		
		md.addAttribute("memberInfo", memberInfo);
		
		return "/usr/member/userInfo";
	}
	
	@RequestMapping("/usr/member/doDelete")
	public String doDelete(HttpSession session) {
		
		Member loginedMember = (Member) session.getAttribute("loginedMember");
		String accessToken = (String) session.getAttribute("accessToken");
		session.removeAttribute("loginedMember");
		session.removeAttribute("accessToken");
		
		kakaoAPI.kakaoDisconnect(accessToken);
		
		memberService.doDelete(loginedMember);
		
		return "/usr/home/main";
	}
}
