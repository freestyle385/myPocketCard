package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ResultData;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {

	MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public ResultData<String> doJoin(String infoOrigin, String userEmail, String userName) {
		
		memberRepository.doJoin(infoOrigin, userEmail, userName);
		
		return null;
	}
}
