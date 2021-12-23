package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public ResultData<ArrayList<Card>> getCardList(
			int memberId, 
			String hashTag, 
			int learningStatus, 
			int answerHideStatus, 
			String searchKeyword, 
			int curPage) {
		
		ResultData<ArrayList<Card>> listRd = cardService.getCardList(memberId, hashTag, learningStatus, answerHideStatus, searchKeyword, curPage);
		
		return listRd;
	}
	
	@RequestMapping("/usr/card/setCardCondition")
	@ResponseBody
	public String setCardCondition(String cardId, int memberId, Integer learningStatus, Integer answerHideStatus) {
		ResultData<String> setRd = cardService.setCardCondition(cardId, memberId, learningStatus, answerHideStatus);
		return setRd.getMsg();
	}
	
	@RequestMapping("/usr/card/detail")
	@ResponseBody
	public String getCardDetail(int cardId, int memberId) {
		ResultData<Card> cardRd = cardService.getCardDetail(cardId, memberId);
		return "";
	}
	
	@RequestMapping("/usr/card/doWrite")
	@ResponseBody
	public String doWriteCard(@ModelAttribute ForWriteCard card){
		ResultData<String> writeRd = cardService.doWriteCard(card);
		return writeRd.getMsg();
	}
	
	@RequestMapping("/usr/card/showWrite")
	@ResponseBody
	public String showWriteCard() {
		return "/usr/card/write";
	}
}
