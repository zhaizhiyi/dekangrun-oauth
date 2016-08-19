package com.htche.oauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htche.oauth.dao.OauthClientDetailsDao;
import com.htche.oauth.entity.OauthClientDetails;
import com.htche.oauth.service.IOauthClientDetailsService;
@Service
@Transactional(rollbackFor = Exception.class)
public class OauthClientDetailsServiceImpl implements IOauthClientDetailsService{

	@Autowired
	OauthClientDetailsDao oauthClientDetailsDao;
	@Override
	public OauthClientDetails selectByClientId(String clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerClientDetails(OauthClientDetails formDto) {
		OauthClientDetails oauthClientDetails=formDto.createDomain();
		oauthClientDetailsDao.insert(oauthClientDetails);
	}
}
