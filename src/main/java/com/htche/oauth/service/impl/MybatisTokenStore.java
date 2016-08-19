package com.htche.oauth.service.impl;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.common.ExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import com.htche.oauth.dao.OauthAccessTokenDao;
import com.htche.oauth.dao.OauthRefreshTokenDao;
import com.htche.oauth.entity.OauthAccessToken;
import com.htche.oauth.entity.OauthRefreshToken;


@Service
public class MybatisTokenStore implements TokenStore{
	private static final Log log=LogFactory.getLog(MybatisTokenStore.class);
	@Autowired
	private OauthAccessTokenDao oauthAccessTokenDao;
	@Autowired
	private OauthRefreshTokenDao oauthRefreshTokenDao;
	private AuthenticationKeyGenerator authenticationKeyGenerator=new DefaultAuthenticationKeyGenerator();

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
		OAuth2AccessToken accessToken=null;
		try {
			String auth=authenticationKeyGenerator.extractKey(authentication);
			OauthAccessToken at=oauthAccessTokenDao.getAccessToken(auth);
			if(null==at)
			{
				return null;
			}
			else
			{
				accessToken=SerializationUtils.deserialize(at.getToken());
			}
		}
		catch (EmptyResultDataAccessException e) {
			if(log.isInfoEnabled())
			{
				log.debug("Failed to find access token for authentication "+authentication);
			}
		}
		return accessToken;
	}

	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		OAuth2AccessToken accessToken=null;
		try {
			accessToken=SerializationUtils.deserialize(oauthAccessTokenDao.readAccessToken(tokenValue).getToken());
		}
		catch (EmptyResultDataAccessException e) {
			if(log.isInfoEnabled())
			{
				log.info("Failed to find access token for token "+tokenValue);
			}
		}
		return accessToken;
	}

	@Override
	public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
		OAuth2Authentication authentication=null;
		try {
			OauthAccessToken at=oauthAccessTokenDao.readAccessToken(token.getValue());
			authentication=SerializationUtils.deserialize(at.getAuthentication());
		}
		catch (EmptyResultDataAccessException e) {
			if(log.isInfoEnabled())
			{
				log.info("Failed to find access token for token "+token);
			}
		}
		return authentication;
	}

	public OAuth2Authentication readAuthentication(ExpiringOAuth2RefreshToken token)
	{
		OAuth2Authentication authentication=null;
		try {
			authentication=SerializationUtils.deserialize(oauthRefreshTokenDao.readRefreshToken(token.getValue()).getAuthentication());
		}
		catch (Exception e) {
			if(log.isInfoEnabled())
			{
				log.info("Failed to find access token for token "+token);
			}
		}
		
		return authentication;
	}
	@Override
	public OAuth2Authentication readAuthentication(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OAuth2RefreshToken readRefreshToken(String tokenValue) {
		ExpiringOAuth2RefreshToken refreshToken=null;
		try {
			refreshToken=SerializationUtils.deserialize(oauthRefreshTokenDao.readRefreshToken(tokenValue).getToken());
		}
		catch (EmptyResultDataAccessException e) {
			if(log.isInfoEnabled())
			{
				log.info("Failed to find refresh token for token "+tokenValue);
			}
		}
		return refreshToken;
	}

	@Override
	public void removeAccessToken(OAuth2AccessToken token) {
		
		oauthAccessTokenDao.deleteByPrimaryKey(token.getValue());
	}

	public void removeAccessToken(String tokenValue)
	{
		oauthAccessTokenDao.deleteByPrimaryKey(tokenValue);
	}
	
	public void removeAccessTokenUsingRefreshToken(String refreshToken) {
		oauthAccessTokenDao.removeAccessTokenUsingRefreshToken(refreshToken);
		
	}
	@Override
	public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken token) {
		oauthAccessTokenDao.removeAccessTokenUsingRefreshToken(token.getValue());
		
	}

	public void removeRefreshToken(String tokenValue) {
		
		oauthRefreshTokenDao.removeRefreshToken(tokenValue);
	}
	@Override
	public void removeRefreshToken(OAuth2RefreshToken token) {
		
		oauthRefreshTokenDao.removeRefreshToken(token.getValue());
	}

	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		String refreshToken=null;
		if(token.getRefreshToken()!=null)
		{
			refreshToken=token.getRefreshToken().getValue();
		}
		OauthAccessToken at=new OauthAccessToken();
		at.setTokenId(token.getValue());
		at.setToken(SerializationUtils.serialize(token));
		at.setAuthenticationId(authenticationKeyGenerator.extractKey(authentication));
		at.setAuthentication(SerializationUtils.serialize(authentication));
		at.setRefreshToken(refreshToken);
		System.out.println(authentication.getUserAuthentication().getName());
		oauthAccessTokenDao.storeAccessToken(at);
	}

	public void storeRefreshToken(ExpiringOAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
		OauthRefreshToken rt=new OauthRefreshToken();
		rt.setTokenId(refreshToken.getValue());
		rt.setToken(SerializationUtils.serialize(refreshToken));
		rt.setAuthentication(SerializationUtils.serialize(authentication));
		oauthRefreshTokenDao.storeRefreshToken(rt);
	}
	@Override
	public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
		OauthRefreshToken rt=new OauthRefreshToken();
		rt.setTokenId(refreshToken.getValue());
		rt.setToken(SerializationUtils.serialize(refreshToken));
		rt.setAuthentication(SerializationUtils.serialize(authentication));
		oauthRefreshTokenDao.insertSelective(rt);
	}
	
}
