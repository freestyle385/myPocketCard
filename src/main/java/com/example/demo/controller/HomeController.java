package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Util.LoginStatus;
import com.example.demo.service.CardService;

@Controller
public class HomeController {
	CardService cardService;
	LoginStatus ls;
	
	public HomeController(CardService cardService, LoginStatus ls) {
		this.cardService = cardService;
		this.ls = ls;
	}
	
	@RequestMapping("/")
	public String Home() {
		return "redirect:/usr/home/main";
	}
	
	@RequestMapping("/usr/home/main")
	public String HomeRedirect(Model md) {
		
		if (ls.isLogined() == true) {
			Integer loginedMemberId = ls.getLoginedMember().getId();
			int needLearningCnt = cardService.getNeedLearningCnt(loginedMemberId);
			int complLearningCnt = cardService.getComplLearningCnt(loginedMemberId);
			int allLearningCnt = needLearningCnt + complLearningCnt;
			float complLearningRate = Math.round(((float)complLearningCnt/((float)needLearningCnt+(float)complLearningCnt)) * 100);
			
			md.addAttribute("recentHashTag", cardService.getRecentHashTag(loginedMemberId));
			md.addAttribute("needLearningCnt", needLearningCnt);
			md.addAttribute("complLearningCnt", complLearningCnt);
			md.addAttribute("allLearningCnt", allLearningCnt);
			md.addAttribute("complLearningRate", complLearningRate);
		}
		
		return "/usr/home/main";
	}
}
