package com.htche.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.htche.oauth.service.impl.ClientDetailsUserService;
/**
 * 资源服务
 * @author zhaizhiyi
 * 2016年4月26日
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServiceConfig extends ResourceServerConfigurerAdapter{
	

	@Autowired private TokenStore tokenStore;
	@Autowired private ClientDetailsUserService clientDetailsUserService;
	
	
	
	@Bean
	public OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler() {
	    return new OAuth2AccessDeniedHandler();
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception
	{
		resources.resourceId("jaxenter").tokenStore(tokenStore);
	}
	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		
		http.anonymous().disable();
		
		http.requestMatchers().antMatchers("/app/**").and().authorizeRequests().antMatchers("/app/**").access("#oauth2.hasScope('read')")
		.and().authorizeRequests().antMatchers("/app/**").access("#oauth2.clientHasRole('ROLE_ADMIN')");
		
	}
	
	private String getUUID(String name) {
		String str = name;
		if (name.length() <= 32) {
			// 累加至32位uuid
			for (int i = 0; i < 32 - name.length(); i++) {
				str += '0';
			}
		} else {
			// 删除至32位
			str = name.substring(0, 32);
		}
		return str;
	}
}
