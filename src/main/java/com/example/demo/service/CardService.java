package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
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

	public ResultData<ArrayList<Card>> getCardList(int memberId, String tagStatus, int learningStatus,
			String searchKeyword) {

		ArrayList<Card> allCardList = cardRepository.getCardList(memberId, tagStatus, learningStatus, searchKeyword);

		ResultData<ArrayList<Card>> listRd = new ResultData<>("S-1", "카드리스트, extraDataInfo:allCardListSize",
				allCardList, allCardList.size() + "");

		return listRd;
	}

	public ResultData<Card> getCardDetail(int cardId, int memberId) {

		Card card = cardRepository.getCardDetail(cardId, memberId);

		if (Util.emptyChk(card)) {
			return new ResultData<Card>("F-1", "존재하지 않는 글 입니다.");
		}

		Map<String, Integer> NextPrevCard = cardRepository.getNextPrev(cardId);

		return new ResultData<Card>("S-1", cardId + "번 카드 조회 완료", card, NextPrevCard);
	}

	public ResultData<Integer> doWriteCard(ForWriteCard card) {

		ArrayList<String> nullField = Util.fieldChk(card);

		if (nullField.size() > 0) {
			// 입력되지 않은 값 배열
			return new ResultData<Integer>("F-1", "입력되지 않은 값이 있습니다.", nullField.size(), String.join(",", nullField));
		}

		cardRepository.doWriteCard(card);
		Integer lastInsertId = cardRepository.getLastInsertId();

		return new ResultData<Integer>("S-1", "카드 생성 완료", lastInsertId);
	}

	public ResultData<String> setCardCondition(String cardId, int memberId, Integer learningStatus) {
		ArrayList<Integer> cardIdArr = new ArrayList<>();

		for (String s : cardId.split(",")) {
			cardIdArr.add(Integer.parseInt(s));
		}
		cardRepository.setCardCondition(cardIdArr, memberId, learningStatus);
		return new ResultData<String>("S-1", "카드 상태 변경 완료");
	}

	public ResultData<Integer> doModify(ForWriteCard card, int cardId) {
		cardRepository.doModify(card, cardId);
		return new ResultData<Integer>("S-1", "카드 수정 완료", cardId);
	}

	public SortedSet<String> getAllHashTag(int memberId) {

		String allHashTagStr = cardRepository.getAllHashTag(memberId);

		// 태그 중복제거 set
		if (!Util.emptyChk(allHashTagStr)) {
			SortedSet<String> allHashTag = new TreeSet<>(Arrays.asList(allHashTagStr.split(",")));
			return allHashTag;
		}

		return null;
	}

	public ArrayList<String> getRecentHashTag(int memberId) {
		
		String allHashTagStr = cardRepository.getAllHashTag(memberId);

		if (!Util.emptyChk(allHashTagStr)) {
			// LinkedHashSet에 해시태그 배열을 넣어 입력된 순서를 보장 및 중복 제거
			LinkedHashSet<String> allHashTag = new LinkedHashSet<>(Arrays.asList(allHashTagStr.split(",")));
			
			// set을 ArrayList로 변환하여 인덱스 순서의 역순으로 데이터 정렬(최근 생성 순서)
			ArrayList<String> allHashTagList = new ArrayList<String>(allHashTag);
			Collections.reverse(allHashTagList);
			
			if (allHashTagList.size() < 10) {
				// 해시태그 개수가 10개 미만일 경우, allHashTagList를 내보냄
				return allHashTagList;
			} else {
				// 해시태그 개수가 10개 이상일 경우, sublist로 앞 10개의 해시태그만 추출
				ArrayList<String> recentHashTag = new ArrayList<String>(allHashTagList.subList(0, 10));
				return recentHashTag;
			}
		}
		
		return null;
	}

	public ResultData<String> deleteCard(int cardId, int memberId) {

		Card card = cardRepository.getCardDetail(cardId, memberId);

		if (Util.emptyChk(card)) {
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
