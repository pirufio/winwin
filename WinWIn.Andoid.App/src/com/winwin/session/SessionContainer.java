package com.winwin.session;

import org.apache.http.client.CookieStore;

import com.winwin.models.dto.UserDTO;

public final class SessionContainer {

	public static UserDTO userSession = null;
	
	public static CookieStore sessionCookie;
}
