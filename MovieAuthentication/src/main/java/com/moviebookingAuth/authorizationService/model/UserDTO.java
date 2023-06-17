package com.moviebookingAuth.authorizationService.model;

//used as an intermediary obj for transferring data btwn different layers of the appn

public class UserDTO 
{
	
	private String userName;
	
	private String userPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	
	

}

