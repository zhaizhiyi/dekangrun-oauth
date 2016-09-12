package com.htche.oauth.dao;

import com.htche.oauth.entity.OauthAccessToken;

public interface OauthAccessTokenDao extends BaseDao<OauthAccessToken>{

	void storeAccessToken(OauthAccessToken token);
	OauthAccessToken readAccessToken(String tokenValue);
	void removeAccessToken(String tokenValue);
	void removeAccessTokenUsingRefreshToken(String refreshToken);
	OauthAccessToken getAccessToken(String authentication);
}
