package com.ojas.student.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ojas.student.dao.AccessTokenDao;
import com.ojas.student.entity.AccessToken;

public class AccessTokenServiceImpl implements AccessTokenService {

	@Autowired
	private AccessTokenDao accessTokenDao;

	@Override
	public AccessToken saveAccessToken(AccessToken accessToken) {
		return accessTokenDao.save(accessToken);
	}

	@Override
	public AccessToken getAccessTokenDetail(Long id) {
		return accessTokenDao.getAccessTokenDetail(id);
	}


	
	@Override
	public int getUpdateAccessToken(String expireDate, Long id) {
		
		if(null!=accessTokenDao.getUpdateAccessTokenDetail(expireDate, id))
		{
			return 1;
			
		}
		else
		{
			return 0;
		}
	}

}
