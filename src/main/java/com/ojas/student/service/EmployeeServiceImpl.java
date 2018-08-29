package com.ojas.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojas.student.dao.EmployeeInformationDao;
import com.ojas.student.entity.EmployeeInformation;


public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeInformationDao employeeDao;

	@Override
	public EmployeeInformation saveEmp(EmployeeInformation employeeInformation) {
		return employeeDao.save(employeeInformation);
	}

}
