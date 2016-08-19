package com.htche.oauth.service;

import com.htche.oauth.entity.OauthClientDetails;

public interface IOauthClientDetailsService {

	OauthClientDetails selectByClientId(String clientId);
	void registerClientDetails(OauthClientDetails oauthClientDetails);
}
