package hellojpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {
	@Id @GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	private String name;
	
	// 팀에서 멤버로 가는 것이기 때문에 팀 입장에서 멤버는 일대다 
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<Member>() ; // ArrayList로 초기화해두는 것이 관례. 그래야 add할 때 null 안 뜸.
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// 연관관계용 편의 메소드 - 방법 2
	/*
	public void addMember(Member member) {
		member.setTeam(this);
		members.add(member);
	}
	*/
}
