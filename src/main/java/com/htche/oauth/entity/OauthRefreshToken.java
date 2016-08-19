package com.htche.oauth.entity;

import java.io.Serializable;

public class OauthRefreshToken implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8361444898733749334L;
	private String tokenId;
	private byte[] token;
	private byte[] authentication;
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public byte[] getToken() {
		return token;
	}
	public void setToken(byte[] token) {
		this.token = token;
	}
	public byte[] getAuthentication() {
		return authentication;
	}
	public void setAuthentication(byte[] authentication) {
		this.authentication = authentication;
	}
}
