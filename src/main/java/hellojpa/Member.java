package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // 꼭 넣어야함. 그래야 JPA가 첨 로딩될 때 JPA를 사용하는 애구나, 관리해야겠다고 인식을 함.
public class Member {
	@Id // jpa에게 pk가 뭔지 알려주는 역할
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Member(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	/*
		JPA에서는 내부적으로 리플렉션 같은 것들을 쓰면서 동적으로 객체를 생성해내야돼서 기본 생성자가 필요함.
		그렇지 않으면 아래 에러 발생
		The Java class for mapped type "Member" must define a non-private zero-argument constructor 에러가 남
	 */
	public Member() {
	}
}
