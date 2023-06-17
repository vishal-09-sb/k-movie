package com.moviebookingAuth.authorizationService.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingAuth.authorizationService.model.ForgetPassword;
import com.moviebookingAuth.authorizationService.model.User;
import com.moviebookingAuth.authorizationService.service.UserService;
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping({"/registerNewUser"})
	public HttpStatus registerNewUser(@Valid @RequestBody User user) {
      
		System.out.println("Register new user is called");
		return userService.registerNewUser(user);
    
	}
	
	@GetMapping({"/forAdmin"})
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
		return "Sorry..!!Only accessable to admin";
	}
	
	@GetMapping({"/forUser"})
	@PreAuthorize("hasRole('User')")
	public String forUser() {
		return "Sorry..!!Only accessable to user";
	}
	
	@GetMapping({"/username/forget/{email}/{password}"})
	public String forgotUsername(@PathVariable("email") String email, @PathVariable("password") String password) {
      
		System.out.println("Forgot user name controller method is called");
		System.out.println("Email : " + email + " -  Password : " + password);
		return userService.userNameValidator(email, password);
    
	}
	
	@PostMapping({"/user/forgetpassword"})
	public HttpStatus forgotUserPassword(@RequestBody ForgetPassword forgetPassword) {
      
		System.out.println("Forgot user name controller method is called");
		System.out.println("userName : " + forgetPassword.getUserName());
		System.out.println("Security Question : " + forgetPassword.getSecretQuestion());
		System.out.println("Security Answer : " + forgetPassword.getSecretAnswer());
		System.out.println("New Password : " + forgetPassword.getNewPassword());		
		return userService.updateUserPassword(forgetPassword);
    
	}
	
	
	
	
	
	
	
	
	
	

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	

}
