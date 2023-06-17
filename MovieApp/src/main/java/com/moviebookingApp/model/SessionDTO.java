package com.moviebookingApp.model;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

@Entity(name = "Session")
public class SessionDTO {

	@Id
	@Column(name = "sessionId")
	private int sessionId;

	@Column(name = "userName")
	private String userName;

	@Column(name = "userType")
	private String userType;

	public SessionDTO(int sessionId, String userName, String userType) {
		super();
		this.sessionId = sessionId;
		this.userName = userName;
		this.userType = userType;
	}

	public SessionDTO() {
		super();
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
