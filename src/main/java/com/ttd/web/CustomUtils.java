package com.ttd.web;

import javax.servlet.http.HttpSession;

import com.ttd.user.User;

public class CustomUtils {
	public static final String USER_KEY = "LOGIN_USER";
	public static final String MESSAGE = "ALERT_MESSAGE";
	
	public static boolean isLogin(HttpSession session) {
		Object user = session.getAttribute(USER_KEY);
		if (user == null) {
			return false;
		}
		return true;
	}
	
	public static User getLoginUser(HttpSession session) {
		if (!isLogin(session)) {
			return null;
		}
		return (User)session.getAttribute(USER_KEY);
	}
}
