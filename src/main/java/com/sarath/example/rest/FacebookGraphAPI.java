package com.sarath.example.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import com.sarath.example.model.OAuthAccessTokenParams;
import com.sarath.example.service.FacebookService;
import com.sarath.example.service.FacebookServiceImpl;

@Path("/user")
public class FacebookGraphAPI {

	private final String APP_ID = "1561590967417058";
	private final String APP_SECRET = "93e1b1bcbd240e08f50c4c174e4c28c7";
	private final String ACCESS_TOKEN_URL = "https://graph.facebook.com/oauth/access_token";	

	private static final Logger LOG = Logger.getLogger(FacebookGraphAPI.class);

	private static Map<String, String> tempDB = new HashMap<String, String>();

	@POST
	@Path("/long-lived-token")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response generateLongLiveToken(String accessToken) {
		LOG.info("AccessToken recieved");
		LOG.info("AccessToken: " + accessToken);
		FacebookService facebookService = new FacebookServiceImpl();
		OAuthAccessTokenParams params = new OAuthAccessTokenParams();
		params.setClientId(APP_ID);
		params.setClientSecret(APP_SECRET);
		params.setGrantType("fb_exchange_token");
		params.setRefreshToken(accessToken);
		params.setRequestURL(ACCESS_TOKEN_URL);
		String longLiveToken = facebookService.fetchAccessToken(params);
		LOG.info("LongLiveToken: " + longLiveToken);
		tempDB.put("longLiveToken", longLiveToken);
		return Response.ok("Long live token successfully generated").build();
	}

	@GET
	@Path("/likes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserLikes() {
		if (tempDB.get("longLiveToken") == null) {
			return Response.status(HttpStatus.SC_UNAUTHORIZED).build();
		}
		FacebookService facebookService = new FacebookServiceImpl(); 
		OAuthAccessTokenParams params = new OAuthAccessTokenParams();
		params.setClientId(APP_ID);
		params.setClientSecret(APP_SECRET);
		params.setGrantType("fb_exchange_token");		
		params.setRequestURL(ACCESS_TOKEN_URL);
		params.setRefreshToken(tempDB.get("longLiveToken"));
		String likes = facebookService.getUserLikes(params);
		LOG.info("Likes: " + likes);
		return Response.ok(likes).build();
	}

}
