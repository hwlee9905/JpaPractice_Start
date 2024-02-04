package com.jpa.JpaStart;

import com.jpa.model.Member;
import com.jpa.model.Team;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@EntityScan( basePackages = {"com.jpa.model"} )
@Transactional
@Slf4j
class JpaStartApplicationTests {
	@PersistenceContext
	private EntityManager em;

	@Test
	public void create() {

		Team team1 = new Team("team1", "팀1");
		em.persist(team1);
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(team1);
		em.persist(member1);
		Member member2 = new Member("member2", "회원2");
		member2.setTeam(team1);
		em.persist(member2);

		// 테스트 부분은 그대로 유지
		Member retrievedMember1 = em.find(Member.class, member1.getId());
		assertEquals("회원1", retrievedMember1.getUsername());
		assertEquals("팀1", retrievedMember1.getTeam().getName());
	}
	@Test
	void testSaveNonOwner() {
		// 테스트 내용은 그대로 유지
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);
		Member member1 = new Member("member1", "회원1");
		em.persist(member1);
		Member member2 = new Member("member2", "회원2");
		em.persist(member2);

		team1.getMembers().add(member1);
		team1.getMembers().add(member2);

		// 테스트 부분은 그대로 유지
		Member retrievedMember1 = em.find(Member.class, member1.getId());
		Assertions.assertNull(retrievedMember1.getTeam());

	}
	@Test
	void test순수한객체_양방향(){
		Team team1 = new Team("team1", "팀1");
		Member member1 = new Member("member1", "회원1");
		Member member2 = new Member("member2", "회원2");

		member1.setTeam(team1); //연관관계 설정 member1 -> team1
		member2.setTeam(team1); //연관관계 설정 member2 -> team1

		List<Member> members = team1.getMembers();
		log.info("members.size = " + members.size());//결과 : members.size = 0

	}
	@Test
	void test순수한객체_양방향2(){
		Team team1 = new Team("team1", "팀1");
		Member member1 = new Member("member1", "회원1");
		Member member2 = new Member("member2", "회원2");

		member1.setTeam(team1); //연관관계 설정 member1 -> team1
		member2.setTeam(team1); //연관관계 설정 member2 -> team1

		team1.getMembers().add(member1); //연관관계 설정 team1 -> member1
		team1.getMembers().add(member2); //연관관계 설정 team1 -> member2

		List<Member> members = team1.getMembers();
		log.info("members.size = " + members.size());//결과 : members.size = 2

	}
	@Test
	void testORM_양방향(){
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);
		Member member1 = new Member("member1", "회원1");
		em.persist(member1);
		Member member2 = new Member("member2", "회원2");
		em.persist(member2);
		member1.setTeam(team1); //연관관계 설정 member1 -> team1
		member2.setTeam(team1); //연관관계 설정 member2 -> team1

		team1.getMembers().add(member1); //연관관계 설정 team1 -> member1
		team1.getMembers().add(member2); //연관관계 설정 team1 -> member2

		List<Member> members = team1.getMembers();
		log.info("members.size = " + members.size());//결과 : members.size = 2

		Member retrievedMember1 = em.find(Member.class, member1.getId());
		assertEquals("회원1", retrievedMember1.getUsername());
		assertEquals("팀1", retrievedMember1.getTeam().getName());
	}
	void teamORM_양방향_리팩토링(){
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);
		Member member1 = new Member("member1", "회원1");
		Member member2 = new Member("member2", "회원2");
		member1.setTeam(team1); //연관관계 설정 member1 -> team1
		member2.setTeam(team1); //연관관계 설정 member2 -> team1
		em.persist(member1);
		em.persist(member2);
		Member retrievedMember1 = em.find(Member.class, member1.getId());
		assertEquals("회원1", retrievedMember1.getUsername());
		assertEquals("팀1", retrievedMember1.getTeam().getName());

	}


}
