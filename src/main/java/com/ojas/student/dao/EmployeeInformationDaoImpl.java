package com.ojas.student.dao;

import org.springframework.stereotype.Repository;

import com.ojas.student.entity.EmployeeInformation;
@Repository
public class EmployeeInformationDaoImpl extends JpaDao<EmployeeInformation, Long> implements EmployeeInformationDao {

	public EmployeeInformationDaoImpl() {
		super(EmployeeInformation.class);
	}

}
