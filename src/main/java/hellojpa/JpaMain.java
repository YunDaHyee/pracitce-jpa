package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		// unit name이 persistence.xml에서 설정한 persistence-unit명임
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		// 문제 없이 뜨는 거 확인되면 manager 꺼냄
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction(); // 트랜잭션을 얻을 수 있다.
		tx.begin(); // 트잭을 시작시킴

		try {
			
			// 1. 멤버 생성 - 비영속 상태
			/*
				Member member = new Member();
				member.setId(2L);// 1에다가 로그값이어서 L붙인 거
				member.setName("HelloB");
				
				// 멤버 저장 - 영속 상태. 이때 DB에 저장되는 것이 아니다. BEFORE,AFTER 사이에서 로그가 출력되지 않음.
				System.out.println("BEFORE");
				em.persist(member); // 1차 캐시에 저장을 함
				System.out.println("AFTER");
			*/

			// 2. 멤버 조회
			/*
				Member findMember = em.find(Member.class, 1L); // em를 자바 컬렉션처럼 이해하기. 내 대신 객체를 저장해주는 애라고 생각하기
				System.out.println( "찾은 멤버 아이디"+findMember.getId() );
				System.out.println( "찾은 멤버 이름"+findMember.getName() );
				// 이때는 SELECT 쿼리가 나가지 않는다. 위에와 똑같은 PK로 조회를 했기 때문에 1차캐시에 있는 걸로 가져오게 됨.
					
				Member findMember2 = em.find(Member.class, 1L); // 만약 생성은 아까 했고 새로운 트잭이 들어와서 조회하는 기능을 수행해야 한다하면 첫 번째 조회할 떄는 쿼리로 가져옴
				Member findMember3 = em.find(Member.class, 1L); // 방금 조회를 했기 때문에 1차 캐시에서 가져옴
				
				System.out.println(findMember2 == findMember3); // 똑같은 거 조회 시, 두 객체는 같다.
			*/

			// 3. 멤버 삭제
			/*
				Member findMember = em.find(Member.class, 1L); // em를 자바 컬렉션처럼 이해하기. 내 대신 객체를 저장해주는 애라고
				em.remove(findMember);
			*/

			// 4. 멤버 수정
			/*
				Member findMember = em.find(Member.class, 1L); // em를 자바 컬렉션처럼 이해하기. 내 대신 객체를 저장해주는 애라고
				findMember.setName("HelloJPA");
			*/
			/*
				수정 후에 em.persist(findMember); 를 통해서 찾은 멤버를 저장해야할까?
				=>	NO. JPA가 알아서 해주고 자바 컬렉션 다루는 것처럼 설계돼서 그런것임.
					컬렉션에서 값을 꺼내고 값 변경하면 다시 컬렉션에 집어넣지 않는 것처럼.
					jpa를 통해서 엔티티를 가져오면 jpa가 관리르 해.
				 	그리고 jpa가 변경됐는지 안했는지 트잭 커밋 시점에 다 체크를 해.
					그래서 바뀌면 트랜잭션 커밋 직전에 업데이트 쿼리 만들어서 날리고 커밋이 돼. 
			*/

			// 5. JPQL 이용
			/*
				List<Member> result = em.createQuery("SELECT m FROM Member as m", Member.class)
					.setFirstResult(1)
					.setMaxResults(10)
					.getResultList();
				
				for (Member member : result) {
					System.out.println("member.name=" + member.getName());
				}
			*/

			// 6. SQL 저장소에 쿼리,엔티티 둘 다 쌓임
			/*
				Member member1 = new Member( 150L, "A" );
				Member member2 = new Member( 160L, "B" );
				em.persist(member1);
				em.persist(member2);
			*/

			// 7. 값 수정 시, 직접 변경해주는 persist/update/delete문을 사용하지 않아도 된다.
			/*
				Member member = em.find(Member.class, 150L);
				member.setName("ZZZZZ"); //값만 바꿔도 JPA가 UPDATE문을 침.
			*/

			// 8. flush() 예제
			/*
				Member member = new Member(200L, "member200");
				em.persist(member);
				em.flush(); // 트잭 커밋되기 전까지는 이 쿼리를 볼 수 없기 때문에 미리 DB에 반영하거나 쿼리 날라가는 것을 미리 보기 위해서 강제로 호출함. 그러면 방금 일어난 flush 매커니즘이 즉시 일어남. 그다음에 DB 트잭이 커밋된다.
			*/

			// 9. 준영속 상태로 만드는 예제
			/*
				Member member = em.find(Member.class, 150L); // JPA가 DB에서 조회해와서 영속성 컨텍스트에 올림 
				member.setName("AAAAA");
			
				//em.detach(member); // jpa에서 관리안하게 됨. 그래서 트잭 커밋 시에 아무일도 일어나지 않음.
				em.clear();
				Member member2 = em.find(Member.class, 150L); // JPA가 DB에서 조회해와서 영속성 컨텍스트에 올림 
			
			*/
			
			//System.out.println("====================");
			
			// 10. Enumerated - enum 타입 매핑 예제 
			/*
				Member member = new Member();
				member.setId(3L);
				member.setUsername("C");
				member.setRoleType(RoleType.GUEST);
			*/
			
			// 11. 기본키 매핑 예제 
			// @Id
			/*
			Member member = new Member();
			member.setId2("ID_A");
			member.setUsername("A");
			*/
			
			// @GeneratedValue
			Member member = new Member();
			member.setUsername("A");
			Member member2 = new Member();
			member2.setUsername("A");
			Member member3 = new Member();
			member3.setUsername("A");
			
			System.out.println("================");
			em.persist(member);
			em.persist(member2);
			em.persist(member3);
			System.out.println("member.id = " + member.getId2());
			System.out.println("member.id = " + member2.getId2());
			System.out.println("member.id = " + member3.getId2());
			System.out.println("================");
			
			tx.commit(); // 트잭 커밋. 꼭 해줘야 반영 됨. 이때 DB로 쿼리가 날라간다.
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.clear(); // 실제 어플리케이션이 종료되면 엔티티 매니저 팩토리를 닫아줘야한다.
		}

		emf.close(); // 전체 에플리케이션 끝나면, 웹 어플리케이션이면 와스가 내려갈 떄 엔티티매니저팩토리도 닫아줘야 커넥션 풀링이나 리소스가 릴리즈가 됨.

	}

}
