package com.ojas.student.utilities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ojas.student.entity.StudentInformation;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StudentInformation studentInformation;
	
	public CustomUserDetails(StudentInformation studentInformation) {
	
		this.studentInformation = studentInformation;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return studentInformation.getRole().getPrivilages();
	}

	@Override
	public String getPassword() {
		
		return studentInformation.getPassword();
	}

	@Override
	public String getUsername() {

		return studentInformation.getStudentName();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
