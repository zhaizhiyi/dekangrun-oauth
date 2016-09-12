package com.htche.oauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htche.oauth.dao.OauthClientDetailsDao;
import com.htche.oauth.service.IOauthAccessTokenService;
@Service
@Transactional(rollbackFor = Exception.class)
public class OauthAccessTokenServiceImpl implements IOauthAccessTokenService{
	@Autowired
	private OauthClientDetailsDao oauthClientDetailsDao;
}
