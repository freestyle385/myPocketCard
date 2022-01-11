package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ResultData;
import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Member;

@Service
public class MemberService {

	MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public ResultData<String> doJoin(String infoOrigin, String userEmail, String userNickname) {
		
		memberRepository.doJoin(infoOrigin, userEmail, userNickname);
		
		return null;
	}
	
	public Member getMember(String userEmail, String userNickname) {
		return memberRepository.getMember(userEmail, userNickname);
	}
}
