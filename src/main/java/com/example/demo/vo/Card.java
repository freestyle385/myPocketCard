package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
	int id;
	int writerId;
	String title;
	String body;
	int learningStatus;
	int answerStatus;
	String tagStatus;
	String regDate;
	String updateDate;
}
