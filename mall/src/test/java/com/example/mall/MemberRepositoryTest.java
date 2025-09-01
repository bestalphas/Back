package com.example.mall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.mall.domain.Member;
import com.example.mall.domain.MemberRole;
import com.example.mall.repository.MemberRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberRepositoryTest{
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void testInsertMember() {
		for(int i=0; i<10; i++) {
			Member member = Member.builder()
					.email("user"+ i +"@aaa.com")
					.pw(passwordEncoder.encode("1111"))
					.nickname("user" + i)
					.build();
			
			member.addRole(MemberRole.USER);
			
			if(i >= 5) {
				member.addRole(MemberRole.MANGER);
			}
			
			if(i >=8) {
				member.addRole(MemberRole.ADMIN);
			}
			
			memberRepository.save(member);
		}
	}
}
