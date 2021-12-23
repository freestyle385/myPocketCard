package com.example.demo.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.ForWriteCard;
import com.example.demo.vo.Card;

@Mapper
public interface CardRepository {

	ArrayList<Card> getCardList(@Param("memberId") int memberId,@Param("hashTagArr") String[] hashTagArr,@Param("learningStatus") int learningStatus,@Param("answerHideStatus") int answerHideStatus,@Param("searchKeyword") String searchKeyword,@Param("limitStart") int limitStart,@Param("limitRange") int limitRange);

	Card getCardDetail(@Param("cardId") int cardId,@Param("memberId") int memberId);

	void doWriteCard(ForWriteCard card);

	void setCardCondition(@Param("cardIdArr") ArrayList<Integer> cardIdArr,@Param("memberId") int memberId, @Param("learningStatus") Integer learningStatus,@Param("answerHideStatus") Integer answerHideStatus);
	
}
