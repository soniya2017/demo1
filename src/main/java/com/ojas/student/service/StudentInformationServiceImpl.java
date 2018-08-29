package com.ojas.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ojas.student.dao.StudentInformationDao;
import com.ojas.student.entity.StudentInformation;
import com.ojas.student.utilities.AuthenticationProviderImpl;
import com.ojas.student.utilities.CustomUserDetails;

@Service
public class StudentInformationServiceImpl implements StudentInformationService {

	@Autowired
	private StudentInformationDao studentDao;
	
	@Override
	public StudentInformation create(StudentInformation studentInformation) {
		
		return studentDao.save(studentInformation);
	}
	
	@Override
	public StudentInformation findById(Long id) {

		return studentDao.find(id);
	}

	public StudentInformation getStudentBody(String userName) {
		return studentDao.getStudentEntity(userName);
	}

	public BCryptPasswordEncoder returnBcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		StudentInformation studentInformation = getStudentBody(userName);
		CustomUserDetails customUserDetails = new CustomUserDetails(studentInformation);
		return customUserDetails;
	}

	public Authentication getAuthenticatedObject(String userName)
	{
		UserDetails userDetails = loadUserByUsername(userName);
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
		AuthenticationProviderImpl authProvide = new AuthenticationProviderImpl();
		return authProvide.authenticate(authToken);
	}
}
