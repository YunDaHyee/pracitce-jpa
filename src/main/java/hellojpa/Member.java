package hellojpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity // 꼭 넣어야함. 그래야 JPA가 첨 로딩될 때 JPA를 사용하는 애구나, 관리해야겠다고 인식을 함.
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq", initialValue = 1, allocationSize = 50)
//@TableGenerator(name = "MEMBER_SEQ_GENERATOR", table = "MY_SEQUENCES", pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class Member {
	@Id // jpa에게 pk가 뭔지 알려주는 역할
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
	//@GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id2;

	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;

	@Column(name="USERNAME")
	private String username;
	
	// 자바에 맞춘 타입을 쓰면 DB에서 그에 적절한 타입으로 만든다. 여기서는 Integer에 적절한 NUMBER 타입
	private Integer age;
	
	// 객체에서 ENUM 타입을 쓸 경우 DB에는 ENUM 타입은 기본적으로(어떤 덴 비슷한 거 있음) 없기 때문에 Enumerated를 써야함
	@Enumerated(EnumType.STRING)
	private RoleType roleType;
	
	// 날짜 타입.
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	private LocalDate testLocalDate;
	private LocalDateTime testLocalDateTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	@Lob
	private String description;
	
	@Transient
	private int temp;
	
	/*
		JPA에서는 내부적으로 리플렉션 같은 것들을 쓰면서 동적으로 객체를 생성해내야돼서 기본 생성자가 필요함.
		그렇지 않으면 아래 에러 발생
		The Java class for mapped type "Member" must define a non-private zero-argument constructor 에러가 남
	 */
	public Member() {
	}

	public Team getTeam() {
		return team;
	}
	
	public String getUsername() {
		return username;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setId2(Long id2) {
		this.id2 = id2;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	// 연관관계용 편의 메소드 - 방법 1
	public void changeTeam(Team team) {
		this.team = team;
		team.getMembers().add(this); // this : 나 자신
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public Long getId2() {
		return id2;
	}
}
