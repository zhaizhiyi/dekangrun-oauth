package com.htche.oauth.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.stereotype.Component;

import com.htche.oauth.entity.HtcheUserDetails;
import com.htche.oauth.service.IUserService;
@Component
public class UserServiceImpl  implements IUserService{

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.equals(""))
		{
			throw new UsernameNotFoundException("用户名不能为空.");
		}
		
		return new HtcheUserDetails(username,"password",true,true,true,true,getGrantedAuthorities(username));
//		Users user=new Users();
//		user.setUsername("John");
//		user.setPassword("password");
//		return new WdcyUserDetails(user);
	}
	private Collection< GrantedAuthority> getGrantedAuthorities(String username)
	{
		Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		if(username.equals("John"))
		{
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			authorities.add(new SimpleGrantedAuthority("ROLE_BASIC"));
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			authorities.add(new SimpleGrantedAuthority("USER"));
			authorities.add(new SimpleGrantedAuthority("BASIC"));
			authorities.add(new SimpleGrantedAuthority("ROLE_APP"));
			authorities.add(new SimpleGrantedAuthority("APP"));
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
			
		}
		else
		{
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			authorities.add(new SimpleGrantedAuthority("USER"));
		}
		return authorities;
	}
}
