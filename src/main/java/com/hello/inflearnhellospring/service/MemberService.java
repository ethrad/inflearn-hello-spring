package com.hello.inflearnhellospring.service;

import java.util.List;
import java.util.Optional;

import com.hello.inflearnhellospring.domain.Member;
import com.hello.inflearnhellospring.repository.MemberRepository;
import com.hello.inflearnhellospring.repository.MemoryMemberRepository;

public class MemberService {

	private final MemberRepository memberRepository = new MemoryMemberRepository();

	/**
	 * 회원 가입
	 * @param member 특정 멤버
	 * @return 멤버 아이디
	 */
	public Long join(Member member) {
		validateDuplicateMember(member); // 중복 회원 검증

		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 회원입니다,");
			});
	}

	/**
	 * 전체 회원 조회
	 * @return 전체 멤버 리스트
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}

	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
