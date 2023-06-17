package com.moviebookingAuth.authorizationService.model;

import javax.persistence.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Users")
public class User {
	@Id
	@Column(unique = true)
	private String userName;

	@NotBlank(message = "Mandatory field")
	private String fullName;

	@NotBlank
	@Column(unique = true)
	@Email(message = "Please enter valid Email")
	private String email;

	@NotBlank
	private String userPassword;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private Set<Role> role;

	@NotBlank
	private String secretQuestion;

	@NotBlank
	private String secretAnswer;

	public User(String userName, @NotBlank(message = "Mandatory field") String fullName,
			@NotBlank @Email(message = "Please enter valid Email") String email, @NotBlank String userPassword,
			Set<Role> role, @NotBlank String secretQuestion, @NotBlank String secretAnswer) {
		super();
		this.userName = userName;
		this.fullName = fullName;
		this.email = email;
		this.userPassword = userPassword;
		this.role = role;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

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

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
