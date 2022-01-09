package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Util.Util;
import com.example.demo.dto.ForWriteCard;
import com.example.demo.dto.ResultData;
import com.example.demo.service.CardService;
import com.example.demo.vo.Card;

@Controller
public class CardController {
	CardService cardService;
	
	public CardController(CardService cardService) {
		this.cardService = cardService;
	}
	
	@RequestMapping("/usr/card/list")
	public String getCardList(
			int memberId, 
			String hashTag, 
			@RequestParam(defaultValue = "-1") int learningStatus, 
			String searchKeyword, 
			@RequestParam(defaultValue = "1") int curPage,
			Model md) {
		
		//listRd info (결과 코드, 데이터 정보, 카드리스트, 전체카드의 수(Int))
		ResultData<ArrayList<Card>> listRd = cardService.getCardList(memberId, hashTag, learningStatus, searchKeyword, curPage);
		
		md.addAttribute("listRd", listRd);
		md.addAttribute("allHashTag", cardService.getAllHashTag(memberId));
		
		return "/usr/card/list";
	}
	
	@RequestMapping("/usr/card/setCardCondition")
	@ResponseBody
	public String setCardCondition(String cardId, int memberId, Integer learningStatus) {
		ResultData<String> setRd = cardService.setCardCondition(cardId, memberId, learningStatus);
		return setRd.getMsg();
	}
	
	@RequestMapping("/usr/card/detail")
	@ResponseBody
	public String getCardDetail(Model md, int cardId, int memberId) {
		
		//cardRd 정보 (결과 코드, 결과 메세지, 카드VO, 이전 다음카드의 id 배열)
		ResultData<Card> cardRd = cardService.getCardDetail(cardId, memberId);
		md.addAttribute("cardRd", cardRd);
		
		return "/usr/card/detail";
	}
	
	@RequestMapping("/usr/card/doWrite")
	@ResponseBody
	public String doWriteCard(@ModelAttribute ForWriteCard card){
		ResultData<String> writeRd = cardService.doWriteCard(card);
		return writeRd.getMsg();
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
	public String showModify(Model md, int cardId, int memberId) {
		
		ResultData<Card> cardRd = cardService.getCardDetail(cardId, memberId);
		// 수정하려는 카드의 기존 상태
		md.addAttribute("cardRd", cardRd);
		
		return "/usr/card/modify";
	}
}
