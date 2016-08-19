package com.htche.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;

import com.htche.oauth.entity.OauthClientDetails;
import com.htche.oauth.service.IOauthClientDetailsService;

/**
 * @author Shengzhao Li
 */
public class OauthUserApprovalHandler extends TokenStoreUserApprovalHandler {

	@Autowired
    private IOauthClientDetailsService oauthClientDetailsService;

    public OauthUserApprovalHandler() {
    }


    public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        if (super.isApproved(authorizationRequest, userAuthentication)) {
            return true;
        }
        if (!userAuthentication.isAuthenticated()) {
            return false;
        }

        OauthClientDetails clientDetails = oauthClientDetailsService.selectByClientId(authorizationRequest.getClientId());
        return clientDetails != null && clientDetails.trusted();

    }


	public void setOauthClientDetailsService(IOauthClientDetailsService oauthClientDetailsService) {
		this.oauthClientDetailsService = oauthClientDetailsService;
	}

    
}
