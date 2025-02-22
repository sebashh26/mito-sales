package com.mitocode.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
//el hecho de anotar una clase spring crea un bean en el contenedor paar poderlo utilizar con injeccion de dependencias
@Slf4j
@Service
public class AuthServiceImpl {

	public boolean hasAccess(String path) {
		
		boolean status = true;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();		
		 authentication.getAuthorities().forEach(e -> log.info(e.getAuthority()));
//		 GrantedAuthority role = authentication.getAuthorities().stream().findFirst().orElse(null);
//		if( role!=null && "ADMIN".equals(role.getAuthority())) {
//			status = true;
//		}
		//log.info(role.getAuthority());
		log.info(username);
		return status;
	}
}
