package com.ojas.student.dao;

import com.ojas.student.entity.AccessToken;

public class AccessTokenDaoImpl extends JpaDao<AccessToken, Long> implements AccessTokenDao{

	public AccessTokenDaoImpl() {
		super(AccessToken.class);
	}	
}
