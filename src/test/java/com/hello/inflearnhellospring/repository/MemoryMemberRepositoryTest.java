package com.hello.inflearnhellospring.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.hello.inflearnhellospring.domain.Member;

public class MemoryMemberRepositoryTest {
	MemoryMemberRepository repository = new MemoryMemberRepository();

	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}

	@Test
	public void save() {
		Member member = new Member();
		member.setName("test");

		repository.save(member);

		Member result = repository.findById(member.getId()).get();
		Assertions.assertEquals(result, member);
	}

	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("test1");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("test2");
		repository.save(member2);

		Member result = repository.findByName("test1").get();

		assertThat(result).isEqualTo(member1);
	}

	@Test
	public void findAll() {
		//given
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		//when
		List<Member> result = repository.findAll();
		//then
		assertThat(result.size()).isEqualTo(2);
	}

}
