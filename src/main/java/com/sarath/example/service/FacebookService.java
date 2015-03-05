package com.sarath.example.service;

import com.sarath.example.model.OAuthAccessTokenParams;

public interface FacebookService {	

	String fetchAccessToken(OAuthAccessTokenParams params);

	String getUserLikes(OAuthAccessTokenParams params);
	
}
