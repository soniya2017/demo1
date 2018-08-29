package com.ojas.student.utilities;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationManagerImpl implements AuthenticationManager {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(authentication);
		return context.getAuthentication();
	}

}
