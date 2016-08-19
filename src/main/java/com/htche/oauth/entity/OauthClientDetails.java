package com.htche.oauth.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class OauthClientDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8284478699185825895L;
	private String clientId;
	private String resourceIds;
	private String clientSecret;
	private String scope;
	private String authorizedGrantTypes;
	private String webServerRedirectUri;
	private String authorities;
	private Integer accessTokenValidity;
	private Integer refreshTokenValidity;
	private String additionalInformation;
	private String autoapprove;
	private boolean trusted = false;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getResourceIds() {
		return resourceIds;
	}
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}
	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}
	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}
	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}
	public String getAuthorities() {
		return authorities;
	}
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}
	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}
	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}
	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}
	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	public String getAutoapprove() {
		return autoapprove;
	}
	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}
	public boolean trusted() {
		return trusted;
	}
	public void setTrusted(boolean trusted) {
		this.trusted = trusted;
	}
	public OauthClientDetails clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public OauthClientDetails clientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public OauthClientDetails resourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
        return this;
    }

    public OauthClientDetails authorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
        return this;
    }

    public OauthClientDetails scope(String scope) {
        this.scope = scope;
        return this;
    }
    public OauthClientDetails webServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
        return this;
    }
    public OauthClientDetails authorities(String authorities) {
        this.authorities = authorities;
        return this;
    }
    public OauthClientDetails accessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
        return this;
    }
    public OauthClientDetails refreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
        return this;
    }
    public OauthClientDetails trusted(boolean trusted) {
        this.trusted = trusted;
        return this;
    }
    public OauthClientDetails additionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }
	public OauthClientDetails createDomain() {
        OauthClientDetails clientDetails = new OauthClientDetails()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .resourceIds(resourceIds)
                .authorizedGrantTypes(authorizedGrantTypes)
                .scope(scope);

        if (StringUtils.isNotEmpty(webServerRedirectUri)) {
            clientDetails.webServerRedirectUri(webServerRedirectUri);
        }

        if (StringUtils.isNotEmpty(authorities)) {
            clientDetails.authorities(authorities);
        }

        clientDetails.accessTokenValidity(accessTokenValidity)
                .refreshTokenValidity(refreshTokenValidity)
                .trusted(trusted);

        if (StringUtils.isNotEmpty(additionalInformation)) {
            clientDetails.additionalInformation(additionalInformation);
        }

        return clientDetails;
    }
}
