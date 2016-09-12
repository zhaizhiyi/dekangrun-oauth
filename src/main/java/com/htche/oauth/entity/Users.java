package com.htche.oauth.entity;

import java.util.ArrayList;
import java.util.List;

public class Users {
	private String username;
	private String password;
	private Integer enabled;
	private boolean defaultUser = false;
	private List<Privilege> privileges = new ArrayList<>();
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	public boolean defaultUser() {
        return defaultUser;
    }
	public List<Privilege> privileges() {
        return privileges;
    }
}
