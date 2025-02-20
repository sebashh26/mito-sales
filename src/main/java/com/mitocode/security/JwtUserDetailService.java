package com.mitocode.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mitocode.model.User;
import com.mitocode.repo.IUserRepo;

import lombok.RequiredArgsConstructor;
//clase ss2
@Service
@RequiredArgsConstructor
public class JwtUserDetailService implements UserDetailsService {
	
	private final IUserRepo repo;

	@Override//se le dice en donde esta los usuarios claves y roles
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	 
		User user =  repo.findOneByUserName(username);
		
		if (user == null) {
			
			 throw new UsernameNotFoundException("User not found: " + username);
		} 
		
		List<GrantedAuthority> roles = new ArrayList<>();
		String role = user.getRole().getName();
		roles.add(new SimpleGrantedAuthority(role));
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(), roles);
	}

}
