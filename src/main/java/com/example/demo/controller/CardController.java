package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Util.LoginStatus;
import com.example.demo.Util.Util;
import com.example.demo.dto.ForWriteCard;
import com.example.demo.dto.ResultData;
import com.example.demo.service.CardService;
import com.example.demo.vo.Card;

@Controller
public class CardController {
	CardService cardService;
	LoginStatus ls;
	
	public CardController(CardService cardService, LoginStatus ls) {
		this.cardService = cardService;
		this.ls = ls;
	}
	
	@RequestMapping("/usr/card/list")
	public String getCardList(
			String tagStatus, 
			@RequestParam(defaultValue = "-1") int learningStatus, 
			String searchKeyword,
			Model md) {
		
		int loginedMemberId = ls.getLoginedMember().getId();
		
		//listRd info (결과 코드, 데이터 정보, 카드리스트, 전체카드의 수(Int))
		ResultData<ArrayList<Card>> listRd = cardService.getCardList(loginedMemberId, tagStatus, learningStatus, searchKeyword);
		
		md.addAttribute("listRd", listRd);
		md.addAttribute("allHashTag", cardService.getAllHashTag(loginedMemberId));
		md.addAttribute("searchedLearn", learningStatus);
		md.addAttribute("searchedTag", tagStatus);
		
		if (searchKeyword != null) {
			md.addAttribute("searchedKeyword", searchKeyword);
		}
		
		return "/usr/card/list";
	}
	
	@RequestMapping("/usr/card/setCardCondition")
	@ResponseBody
	public String setCardCondition(String cardId, Integer learningStatus) {
		
		int loginedMemberId = ls.getLoginedMember().getId();
		
		cardService.setCardCondition(cardId, loginedMemberId, learningStatus);
		
		return Util.jsReplace("", String.format("/usr/card/list"));
	}
	
	@RequestMapping("/usr/card/detail")
	public String getCardDetail(Model md, int cardId, HttpServletResponse resp) {
		
		int loginedMemberId = ls.getLoginedMember().getId();
		
		//cardRd 정보 (결과 코드, 결과 메세지, 카드VO, 이전 다음카드의 id 배열)
		ResultData<Card> cardRd = cardService.getCardDetail(cardId, loginedMemberId);
		
		if(cardRd.isFail()) {
			try {
				Util.javaHistoryBack(resp, cardRd.getMsg());
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
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
	@ResponseBody
	public String doModify(@ModelAttribute ForWriteCard card, int cardId) {
		
		card.setWriterId(ls.getLoginedMember().getId());
		if(Util.fieldChk(card).size() > 0) {
			return Util.jsHistoryBack("입력되지 않은 값이 있습니다.");
		}
		ResultData<Integer> modifyRd = cardService.doModify(card, cardId);
		
		if(modifyRd.isFail()) {
			return Util.jsHistoryBack(modifyRd.getMsg());
		}
		
		//카드 수정 후 수정된 카드의 detail 페이지로 이동
		return Util.jsReplace("", String.format("/usr/card/detail?cardId=%d&memberId=%d", cardId, card.getWriterId())); 
	}
	
	@RequestMapping("/usr/card/showModify")
	public String showModify(Model md, int cardId, HttpServletResponse resp) {
		
		int loginedMemberId = ls.getLoginedMember().getId();
		ResultData<Card> cardRd = cardService.getCardDetail(cardId, loginedMemberId);
		
		if(cardRd.isFail()) {
			try {
				Util.javaHistoryBack(resp, cardRd.getMsg());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// 수정하려는 카드의 기존 상태
		md.addAttribute("cardRd", cardRd);
		
		return "/usr/card/modify";
	}
	
	@RequestMapping("/usr/card/doDelete")
	@ResponseBody
	public String doDelete(Model md, int cardId, HttpServletResponse resp) {
		
		int loginedMemberId = ls.getLoginedMember().getId();
		ResultData<String> deleteRd = cardService.deleteCard(cardId, loginedMemberId);
		
		if(deleteRd.isFail()) {
			return Util.jsHistoryBack(deleteRd.getMsg());
		}
		
		return Util.jsReplace("", String.format("/usr/card/list"));
	}
	
}
