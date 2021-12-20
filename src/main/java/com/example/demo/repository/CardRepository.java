package com.example.demo.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.ForWriteCard;
import com.example.demo.vo.Card;

@Mapper
public interface CardRepository {

	ArrayList<Card> getCardList(@Param("memberId") int memberId);

	Card getCardDetail(@Param("cardId") int cardId,@Param("memberId") int memberId);

	void doWriteCard(ForWriteCard card);
	
}
