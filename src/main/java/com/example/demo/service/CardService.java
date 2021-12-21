package com.example.demo.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.example.demo.Util.Util;
import com.example.demo.dto.ForWriteCard;
import com.example.demo.dto.ResultData;
import com.example.demo.repository.CardRepository;
import com.example.demo.vo.Card;

@Service
public class CardService {
	
	CardRepository cardRepository;
	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	public ResultData<ArrayList<Card>> getCardList(int memberId) {
		
		ArrayList<Card> cardList = cardRepository.getCardList(memberId);
		ResultData<ArrayList<Card>> listRd = new ResultData<>("S-1", "카드리스트", cardList);
		return listRd;
	}

	public ResultData<Card> getCardDetail(int cardId, int memberId) {
		Card card = cardRepository.getCardDetail(cardId, memberId);
		return new ResultData<Card>("S-1", cardId + "번 카드 입니다.", card);
	}

	public ResultData<String> doWriteCard(ForWriteCard card) {
		ArrayList<String> nullField = Util.fieldChk(card);
		if(nullField.size() > 0) {
			return new ResultData<String>("F-1", "입력되지 않은 값이 있습니다.", String.join(",", nullField));
		}
		cardRepository.doWriteCard(card);
		return new ResultData<String>("S-1", "카드 생성 완료");
	}

}
