package com.htche.oauth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.stereotype.Service;
@Service
public class ClientDetailsUserService implements ClientDetailsService{

	private String id;
	private String secretkey;
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		if(clientId.equals(id))
		{
			List<String> authorizedGrantTypes = new ArrayList<String>();  
            authorizedGrantTypes.add("password");  
            authorizedGrantTypes.add("refresh_token");  
            authorizedGrantTypes.add("client_credentials");
            
            BaseClientDetails clientDetails = new BaseClientDetails();  
            clientDetails.setClientId(id);  
            clientDetails.setClientSecret(secretkey);  
            clientDetails.setAuthorizedGrantTypes(authorizedGrantTypes); 
            return clientDetails;
		}
		else
		{
			throw new NoSuchClientException("No client recognized with id: "  + clientId); 
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSecretkey() {
		return secretkey;
	}
	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}

}
