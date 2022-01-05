package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//카드 작성을 위한 DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForWriteCard {
	
	private Integer writerId;
	private String title;
	private String body;
	private Integer learningStatus;
	private String tagStatus = "";	//default Value
}
