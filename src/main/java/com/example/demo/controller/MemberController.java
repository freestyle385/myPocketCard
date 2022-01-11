package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.dto.ResultData;
import com.example.demo.service.MemberService;

@Controller
public class MemberController {
	
	MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/doLogin")
	public String doJoin(String infoOrigin, String userEmail, String userName) {
		
		ResultData<String> joinRd = memberService.doJoin(infoOrigin, userEmail, userName);
		
		return "/usr/member/login";
	}
}
