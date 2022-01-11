package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.vo.Member;

@Mapper
public interface MemberRepository {

	void doJoin(String infoOrigin, String userEmail, String userNickname);
	
	Member getMember(String userEmail, String userNickname);
}
