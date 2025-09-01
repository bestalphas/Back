package com.example.mall.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
//@Setter 
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "memberRoleList")
public class Member {
	@Id
	private String email;
	
	private String pw;
	
	private String nickname;
	
	private boolean social;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Builder.Default
	private List<MemberRole> memberRoleList = new ArrayList<>();
	
	public void addRole(MemberRole memberRole) {
		memberRoleList.add(memberRole);
	}
	
	public void clearRole() {
		memberRoleList.clear();
	}
	
	public void changeNickName(String nickname) {
		this.nickname = nickname;
	}
	
	public void changePw(String pw) {
		this.pw = pw;
	}
	
	public void changeSocial(Boolean social) {
		this.social = social;
	}
}
