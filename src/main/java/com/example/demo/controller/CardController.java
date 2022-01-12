package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Util.Util;
import com.example.demo.Util.loginStatus;
import com.example.demo.dto.ForWriteCard;
import com.example.demo.dto.ResultData;
import com.example.demo.service.CardService;
import com.example.demo.vo.Card;
import com.example.demo.vo.Member;

@Controller
public class CardController {
	CardService cardService;
	loginStatus ls;
	
	public CardController(CardService cardService, loginStatus ls) {
		this.cardService = cardService;
		this.ls = ls;
	}
	
	@RequestMapping("/usr/card/list")
	public String getCardList(
			String hashTag, 
			@RequestParam(defaultValue = "-1") int learningStatus, 
			String searchKeyword, 
			@RequestParam(defaultValue = "1") int curPage,
			Model md) {
		
		int loginedMemberId = ls.getLoginedMember().getId();
		
		//listRd info (결과 코드, 데이터 정보, 카드리스트, 전체카드의 수(Int))
		ResultData<ArrayList<Card>> listRd = cardService.getCardList(loginedMemberId, hashTag, learningStatus, searchKeyword, curPage);
		
		md.addAttribute("listRd", listRd);
		cardService.getAllHashTag(loginedMemberId);
		md.addAttribute("allHashTag", cardService.getAllHashTag(loginedMemberId));
		
		
		
		return "/usr/card/list";
	}
	
	@RequestMapping("/usr/card/setCardCondition")
	@ResponseBody
	public String setCardCondition(String cardId, Integer learningStatus) {
		
		int loginedMemberId = ls.getLoginedMember().getId();
		
		ResultData<String> setRd = cardService.setCardCondition(cardId, loginedMemberId, learningStatus);
		
		return setRd.getMsg();
	}
	
	@RequestMapping("/usr/card/detail")
	public String getCardDetail(Model md, int cardId) {
		
		int loginedMemberId = ls.getLoginedMember().getId();
		
		//cardRd 정보 (결과 코드, 결과 메세지, 카드VO, 이전 다음카드의 id 배열)
		ResultData<Card> cardRd = cardService.getCardDetail(cardId, loginedMemberId);
		md.addAttribute("cardRd", cardRd);
		
		return "/usr/card/detail";
	}
	
	@RequestMapping("/usr/card/doWrite")
	@ResponseBody
	public String doWriteCard(@ModelAttribute ForWriteCard card){
		
		card.setWriterId(ls.getLoginedMember().getId());
		ResultData<Integer> writeRd = cardService.doWriteCard(card);
		
		if(writeRd.isFail()) {
			return Util.jsHistoryBack(writeRd.getMsg());
		}
		
		return Util.jsReplace("", String.format("/usr/card/detail?cardId=%d", writeRd.getData()));
	}
	
	@RequestMapping("/usr/card/showWrite")
	public String showWriteCard() {
		
		return "/usr/card/write";
		
	}
	
	@RequestMapping("/usr/card/doModify")
	public String doModify(@ModelAttribute ForWriteCard card, int cardId) {
		
		cardService.doModify(card, cardId);
		
		//카드 수정 후 수정된 카드의 detail 페이지로 이동
		return String.format("/usr/card/detail?cardId=%d&memberId=%d", cardId, card.getWriterId());
	}
	
	@RequestMapping("/usr/card/showModify")
	public String showModify(Model md, int cardId) {
		int loginedMemberId = ls.getLoginedMember().getId();
		ResultData<Card> cardRd = cardService.getCardDetail(cardId, loginedMemberId);
		// 수정하려는 카드의 기존 상태
		md.addAttribute("cardRd", cardRd);
		
		return "/usr/card/modify";
	}
}
