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
	public String getCardList(int memberId) {
		ResultData<ArrayList<Card>> listRd = cardService.getCardList(memberId);
		return listRd.getData().toString();
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
