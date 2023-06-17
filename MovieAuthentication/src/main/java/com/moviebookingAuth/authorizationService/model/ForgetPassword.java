package com.moviebookingAuth.authorizationService.model;


public class ForgetPassword {
	
	
    private String secretQuestion;
	
    private String secretAnswer;
    
    private String newPassword;
    
    private String userName;

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ForgetPassword(String secretQuestion, String secretAnswer, String newPassword, String userName) {
		super();
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
		this.newPassword = newPassword;
		this.userName = userName;
	}

	public ForgetPassword() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}
