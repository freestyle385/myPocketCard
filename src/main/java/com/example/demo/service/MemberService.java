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

	public ResultData<Member> doJoin(String infoOrigin, String userEmail, String userNickname) {
		
		Member member = memberRepository.getMember(userEmail);
		
		if(member.getDelStatus() == 1) {
			memberRepository.updateMember(member);
		}
		
		memberRepository.doJoin(infoOrigin, userEmail, userNickname);
		
		return new ResultData<Member>("S-1", "로그인 성공", member);
	}
	
	public Member getMember(String userEmail) {
		
		return memberRepository.getMember(userEmail);
		
	}

	public void doDelete(Member loginedMember) {
		
		memberRepository.doDelete(loginedMember);
		
	}
}
