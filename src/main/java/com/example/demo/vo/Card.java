package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
	private int id;
	private int writerId;
	private String title;
	private String body;
	private int learningStatus;
	private String tagStatus;
	private String regDate;
	private String updateDate;
}
