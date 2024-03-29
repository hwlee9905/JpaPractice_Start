package com.jpa.JpaStart;

import com.jpa.model.Member;
import com.jpa.model.Team;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@Slf4j
@SpringBootApplication
public class JpaStartApplication {
	public static void main(String[] args) {
		SpringApplication.run(JpaStartApplication.class, args);
		//엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastart");
		EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
		EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득
		try {
			tx.begin(); //트랜잭션 시작
			queryLogicJoin(em);
			testSave(em);
//			updateRelation(em);
//			deleteRelation(em);
			biDirection(em);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); //트랜잭션 롤백
		} finally {
			em.close(); //엔티티 매니저 종료
		}
		emf.close(); //엔티티 매니저 팩토리 종료
	}
//	public static void logic(EntityManager em) {
//
//		String id = "id1";
//		Member member = new Member();
//		member.setId(id);
//		member.setUsername("지한");
//		member.setAge(2);
//
//		//등록
//		em.persist(member);
//
//		//수정
//		member.setAge(20);
//
//		//한 건 조회
//		Member findMember = em.find(Member.class, id);
//		System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());
//
//		//목록 조회
//		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
//		System.out.println("members.size=" + members.size());
//
//		//삭제
//		em.remove(member);
//		em.setFlushMode(FlushModeType.COMMIT);
//
//
//	}
	private static void testSave(EntityManager em) {
		//팀1 저장
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);

		//회원1 저장
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(team1); //연관관계 설정 member1 -> team1
		em.persist(member1);

		//회원2 저장
		Member member2 = new Member("member2", "회원2");
		member2.setTeam(team1); //연관관계 설정 member2 -> team1
		em.persist(member2);
	}

	private static void queryLogicJoin(EntityManager em) {
		String jpql = "select m from Member m join m.team t where t.name=:teamName";

		List<Member> resultList = em.createQuery(jpql, Member.class)
				.setParameter("teamName", "팀1")
				.getResultList();

		for (Member member : resultList) {
			log.info("[query] member.username = " + member.getUsername());
		}
	}
	private static void updateRelation(EntityManager em){
		//새로운 팀2
		Team team2 = new Team("team2", "팀2");
		em.persist(team2);

		//회원1에 새로운 팀2 설정
		Member member = em.find(Member.class, "member1");
		member.setTeam(team2);
	}

	private static void deleteRelation(EntityManager em) {
		Member member1 = em.find(Member.class, "member1");
		member1.setTeam(null);
	}
	private static void biDirection(EntityManager em) {
		Team team = em.find(Team.class, "team1");
		List<Member> members = team.getMembers(); //(팀 -> 회원)
		//객체 그래프 탐색
		for (Member member : members) {
			log.info("member.username = " + member.getUsername());
		}
	}
}
