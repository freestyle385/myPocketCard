package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {

	void doJoin(String infoOrigin, String userEmail, String userName);
	
}
