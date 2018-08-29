package com.ojas.student.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "Role")
public class Role implements Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String roleName;
	
	
	@OneToMany(targetEntity = Privilages.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name= "role_privileges_id", referencedColumnName="id")
	private List<Privilages> privilages = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Privilages> getPrivilages() {
		return privilages;
	}

	public void setPrivilages(List<Privilages> privilages) {
		this.privilages = privilages;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
