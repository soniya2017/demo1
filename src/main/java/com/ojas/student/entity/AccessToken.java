package com.ojas.student.entity;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@javax.persistence.Entity
@Table
public class AccessToken implements Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String accessToken;
	private String creationDate;
	private String expiryDate;
	@ManyToOne(targetEntity=StudentInformation.class, fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinColumn(name="token_student", referencedColumnName="Id")
	private StudentInformation studentInformation;
	
	public StudentInformation getStudentInformation() {
		return studentInformation;
	}
	public void setStudentInformation(StudentInformation studentInformation) {
		this.studentInformation = studentInformation;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
}
