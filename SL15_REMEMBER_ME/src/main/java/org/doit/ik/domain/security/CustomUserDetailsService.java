package org.doit.ik.domain.security;

import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.mapper.MemberMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Component
@Log4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	private final MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn("❤️❤️❤️MemberMapper.Load User By UserName" + username);
		
		MemberVO memberVO = null;
		try {
			memberVO = this.memberMapper.read(username);
		} catch (ClassNotFoundException e) {
			System.out.println("😂😂MemberMapper.Load User By UserName");
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return memberVO == null ? 
				null : new CustomerUser(memberVO);
	}

}
