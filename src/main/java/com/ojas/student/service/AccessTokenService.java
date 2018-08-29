package com.ojas.student.service;

import com.ojas.student.entity.AccessToken;

public interface AccessTokenService {

	AccessToken saveAccessToken(AccessToken accessToken);
	
	AccessToken getAccessTokenDetail(Long id);
	
	public int getUpdateAccessToken(String expireDate, Long id);
}
