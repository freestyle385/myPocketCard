package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	int id;
	String infoOrigin;
	String userEmail;
	String userName;
	String regDate;
	int delStatus;
	String delDate;
}
