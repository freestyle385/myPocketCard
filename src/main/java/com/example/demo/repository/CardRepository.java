package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.demo.dto.ForWriteCard;
import com.example.demo.vo.Card;

@Mapper
public interface CardRepository {

	ArrayList<Card> getCardList(@Param("memberId") int memberId,@Param("hashTagArr") List<String> hashTagArr,@Param("learningStatus") int learningStatus,@Param("searchKeyword") String searchKeyword);

	Card getCardDetail(@Param("cardId") int cardId,@Param("memberId") int memberId);

	void doWriteCard(ForWriteCard card);

	void setCardCondition(@Param("cardIdArr") ArrayList<Integer> cardIdArr,@Param("memberId") int memberId, @Param("learningStatus") Integer learningStatus);

	void doModify(ForWriteCard card, @Param("cardId") int cardId);

	Map<String, Integer> getNextPrev(@Param("cardId") int cardId);

	String getAllHashTag(int memberId);

	Integer getLastInsertId();

	void doDelete(@Param("cardId") int cardId, @Param("memberId") int memberId);

	int getNeedLearningCnt(@Param("memberId") int memberId);
	
	int getComplLearningCnt(@Param("memberId") int memberId);
	
}
