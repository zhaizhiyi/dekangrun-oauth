package com.htche.oauth.dao;

import com.htche.oauth.entity.OauthClientDetails;

public interface OauthClientDetailsDao extends BaseDao<OauthClientDetails>{

	OauthClientDetails selectByClientId(String openID);
}
