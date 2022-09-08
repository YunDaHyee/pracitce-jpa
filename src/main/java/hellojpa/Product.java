package hellojpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product {
	@Id @GeneratedValue
	private Long id;
	private String name;
	
	/*
	// 다대다 관계일 때
	@ManyToMany(mappedBy = "products")
	private List<Member> members = new ArrayList<Member>();
	*/
	
	// 다대다 관계를 일대다,다대일 관계로 풀어준 것
	@OneToMany(mappedBy = "product")
	private List<MemberProduct> members = new ArrayList<MemberProduct>();
	
	
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
	
}
