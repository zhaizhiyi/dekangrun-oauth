package com.htche.oauth.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import com.htche.oauth.service.impl.ClientDetailsUserService;
import com.htche.oauth.service.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class TokenConfiguration extends WebSecurityConfigurerAdapter{
	
	private static final Logger logger=Logger.getLogger(TokenConfiguration.class);
	@Autowired ClientDetailsUserService clientDetailsUserService;
	
	@Autowired
	protected void registerAuthentication(final AuthenticationManagerBuilder auth)throws Exception
	{
		//auth.userDetailsService(userDetailsService).passwordEncoder(new Md5PasswordEncoder());
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(getOauth2ClientDetailsUserService());	
		auth.jdbcAuthentication().dataSource(dataSource()).usersByUsernameQuery("select username as principal,password as credentials,true from users where username = ?")
		.authoritiesByUsernameQuery("select username as principal,authority as role from authorities where username = ?").rolePrefix("ROLE_");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").authenticated()
		.and().httpBasic().realmName("OAuth Server");
	}
	
	@Bean
	public JdbcTokenRepositoryImpl tokenRepository()
	{
		JdbcTokenRepositoryImpl j=new JdbcTokenRepositoryImpl();
		j.setDataSource(dataSource());
		return j;
	}
	@Bean(name="clientUserDetailsService")
	public ClientDetailsUserDetailsService getOauth2ClientDetailsUserService()
	{
		return new ClientDetailsUserDetailsService(clientDetailsUserService);
	}
	
	@Bean
	ClientCredentialsTokenEndpointFilter clientCredentialsTokenEndpointFilter()throws Exception
	{
		ClientCredentialsTokenEndpointFilter clientCredentialsTokenEndpointFilter=new ClientCredentialsTokenEndpointFilter("/oauth/token");
		clientCredentialsTokenEndpointFilter.setAuthenticationManager(authenticationManagerBean());
		return clientCredentialsTokenEndpointFilter;
	}
	/**
	 * 密码授权方式必须配AuthenticationManager
	 * 这个Bean用于oauth2的密码授权模式的配置
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean()throws Exception
	{
		return super.authenticationManagerBean();
	}
	@ConfigurationProperties("jdbc")
	@Bean(destroyMethod = "close", name = "dataSource")
	public DataSource dataSource() {
		return new BasicDataSource();
	}
	
 
}
