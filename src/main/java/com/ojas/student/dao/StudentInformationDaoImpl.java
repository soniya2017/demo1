package com.ojas.student.dao;

import org.springframework.stereotype.Repository;

import com.ojas.student.entity.StudentInformation;

@Repository
public class StudentInformationDaoImpl extends JpaDao<StudentInformation, Long> implements StudentInformationDao {

	public StudentInformationDaoImpl() {
		super(StudentInformation.class);
	}	
}
