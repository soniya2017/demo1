package com.ojas.student.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ojas.student.entity.StudentInformation;

public interface StudentInformationService extends UserDetailsService {

	StudentInformation create(StudentInformation studentInformation);

	StudentInformation findById(Long id);

	public BCryptPasswordEncoder returnBcryptPasswordEncoder();

	public StudentInformation getStudentBody(String userName);

	public Authentication getAuthenticatedObject(String userName);
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;

}
