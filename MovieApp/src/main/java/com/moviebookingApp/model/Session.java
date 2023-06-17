package com.moviebookingApp.model;


public class Session {

	private int sessionId;
	private String userName;
	private String userType;

	public Session(int sessionId, String userName, String userType) {
		this.sessionId = sessionId;
		this.userName = userName;
		this.userType = userType;
	}

	public Session() {
		// Default constructor
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
