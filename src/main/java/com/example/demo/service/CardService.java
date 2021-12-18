package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ResultData;
import com.example.demo.repository.CardRepository;
import com.example.demo.vo.Card;

@Service
public class CardService {
	
	CardRepository cardRepository;
	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	public ResultData<ArrayList<Card>> getCardList() {
		
		ArrayList<Card> cardList = cardRepository.getCardList();
		ResultData<ArrayList<Card>> listRd = new ResultData<>("S-1", "카드리스트", cardList);
		return listRd;
	}

}
