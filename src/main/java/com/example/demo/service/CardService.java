package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

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

	public ResultData<ArrayList<Card>> getCardList(int memberId, String tagStatus, int learningStatus, String searchKeyword, int curPage) {
		
		List<String> hashTagArr = new ArrayList<>();
		if(!Util.emptyChk(tagStatus)){
			hashTagArr = Arrays.asList(tagStatus.split(","));
		}
		
		ArrayList<Card> allCardList = cardRepository.getCardList(memberId, hashTagArr, learningStatus, searchKeyword);
		//int limitStart = (curPage - 1) * 4 > allCardList.size() ? allCardList.size() : (curPage - 1) * 4;
		//int limitRange = limitStart + 4 > allCardList.size() ? allCardList.size() : limitStart + 4;
		
		//전체 게시물에서 subList를 통한 Limit		
		//ArrayList<Card> subCardList = new ArrayList<Card>(allCardList.subList(limitStart, limitRange));
		
		ResultData<ArrayList<Card>> listRd = new ResultData<>("S-1", "카드리스트, extraDataInfo:allCardListSize", allCardList, allCardList.size() + "");
		
		return listRd;
	}

	public ResultData<Card> getCardDetail(int cardId, int memberId) {
		
		Card card = cardRepository.getCardDetail(cardId, memberId);
		
		if(Util.emptyChk(card)) {
			return new ResultData<Card>("F-1", "존재하지 않는 글 입니다.");
		}
		
		Map<String, Integer> NextPrevCard = cardRepository.getNextPrev(cardId);
		
		return new ResultData<Card>("S-1", cardId + "번 카드 입니다.", card, NextPrevCard);
	}

	public ResultData<Integer> doWriteCard(ForWriteCard card) {
		
		ArrayList<String> nullField = Util.fieldChk(card);
		
		if(nullField.size() > 0) {
			// 입력되지 않은 값 배열
			return new ResultData<Integer>("F-1", "입력되지 않은 값이 있습니다.", nullField.size(), String.join(",", nullField));
		}
		
		cardRepository.doWriteCard(card);
		Integer lastInsertId = cardRepository.getLastInsertId();
		
		return new ResultData<Integer>("S-1", "카드 생성 완료", lastInsertId);
	}

	public ResultData<String> setCardCondition(String cardId, int memberId, Integer learningStatus) {
		ArrayList<Integer> cardIdArr = new ArrayList<>();
		
		for(String s : cardId.split(",")) {
			cardIdArr.add(Integer.parseInt(s));
		}
		cardRepository.setCardCondition(cardIdArr, memberId , learningStatus);
		return new ResultData<String>("S-1", "변경성공");
	}

	public ResultData<Integer> doModify(ForWriteCard card, int cardId) {
		cardRepository.doModify(card, cardId);
		return new ResultData<Integer>("S-1", "카드 수정 완료", cardId);
	}
	
	public SortedSet<String> getAllHashTag(int memberId){
		
		String allHashTagStr = cardRepository.getAllHashTag(memberId);
		
		// 태그 중복제거 set
		if(!Util.emptyChk(allHashTagStr)){
			SortedSet<String> allHashTag = new TreeSet<>(Arrays.asList(allHashTagStr.split(",")));
			return allHashTag;
		}
		
		return null;
	}

	public ResultData<String> deleteCard(int cardId, int memberId) {
		
		Card card = cardRepository.getCardDetail(cardId, memberId);
		
		if(Util.emptyChk(card)) {
			return new ResultData<String>("F-1", "존재하지 않는 글 입니다.");
		}
		
		cardRepository.doDelete(cardId, memberId);
		return new ResultData<String>("S-1", "카드 삭제 완료");
	}
	
	public int getNeedLearningCnt(int memberId) {
		int needLearningCnt = cardRepository.getNeedLearningCnt(memberId);
		
		return needLearningCnt;
	}
	
	public int getComplLearningCnt(int memberId) {
		int complLearningCnt = cardRepository.getComplLearningCnt(memberId);
		
		return complLearningCnt;
	}
}
