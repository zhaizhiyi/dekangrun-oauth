package com.htche.oauth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.htche.oauth.service.IUserService;
import com.htche.oauth.service.impl.ClientDetailsUserService;
@Configuration
@EnableAuthorizationServer
public class AuthConfiguration extends AuthorizationServerConfigurerAdapter{
	@Autowired private DataSource dataSource;
	@Autowired IUserService userService;
	@Autowired private ClientDetailsUserService clientDetailsUserService;
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Bean
	public TokenStore tokenStore() {
		//return new InMemoryTokenStore();
		return new JdbcTokenStore(dataSource);
	}
	@Bean
	public JdbcApprovalStore jdbcApprovalStore()
	{
		return new JdbcApprovalStore(dataSource);
	}
	
	
  @Autowired
  @Qualifier("authenticationManagerBean")
  private AuthenticationManager authenticationManager;//身份认证管理者
  

  /**
   * 定义授权和令牌端点和令牌服务
   */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws
	Exception {
		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
		endpoints.reuseRefreshTokens(true);
		//.pathMapping("/oauth/authorize", "/oauth2/authorize").pathMapping("/oauth/token", "/oauth2/token");
	}
	/**
	 * 令牌端点定义安全约束
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception
	{
		/**
         * allow表示允许在认证的时候把参数放到url之中传过去
         * @see org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter
         */
		oauthServer.allowFormAuthenticationForClients();
		
		//oauthServer.passwordEncoder(passwordEncoder);
	}
	
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	//clients.inMemory()
		clients.withClientDetails(clientDetailsUserService);
		clients.jdbc(dataSource);
	}
	  @Bean
	  public ApprovalStore approvalStore() throws Exception {
	      TokenApprovalStore store = new TokenApprovalStore();
	      store.setTokenStore(tokenStore());
	      return store;
	  }
	  @Bean
	  @Primary
	  public DefaultTokenServices tokenServices()
	  {
		  DefaultTokenServices tokenServices=new DefaultTokenServices();
		  tokenServices.setSupportRefreshToken(true); 
		  tokenServices.setTokenStore(tokenStore());
		  return tokenServices;
	  }
}
