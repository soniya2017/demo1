package com.ojas.student.dao;

import com.ojas.student.entity.StudentInformation;

public interface StudentInformationDao extends Dao<StudentInformation, Long>{
	
	
	public StudentInformation getStudentEntity(String userName);

}
