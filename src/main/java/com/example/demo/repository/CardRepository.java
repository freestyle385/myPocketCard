package com.example.demo.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Card;

@Mapper
public interface CardRepository {

	ArrayList<Card> getCardList();
	
}
