package com.htche.oauth.dao;

import com.htche.oauth.entity.OauthRefreshToken;

public interface OauthRefreshTokenDao extends BaseDao<OauthRefreshToken>{

	void storeRefreshToken(OauthRefreshToken token);
	OauthRefreshToken readRefreshToken(String tokenValue);
	void removeRefreshToken(String tokenValue);
	
}
