package com.htche.oauth.entity;

public class OauthApprovals {
	private String userId;
	private String clientId;
	private String scope;
	private String status;
	private String expiresat;
	private String lastmodifiedat;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExpiresat() {
		return expiresat;
	}
	public void setExpiresat(String expiresat) {
		this.expiresat = expiresat;
	}
	public String getLastmodifiedat() {
		return lastmodifiedat;
	}
	public void setLastmodifiedat(String lastmodifiedat) {
		this.lastmodifiedat = lastmodifiedat;
	}
}
