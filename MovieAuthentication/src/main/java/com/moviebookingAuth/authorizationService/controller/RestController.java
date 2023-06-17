package com.moviebookingAuth.authorizationService.controller;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import com.moviebookingAuth.authorizationService.model.Role;
import com.moviebookingAuth.authorizationService.service.RoleService;
import com.moviebookingAuth.authorizationService.service.UserService;





@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class RestController {
	
	 @Autowired
	 private RoleService roleservice;  
	 
	 @Autowired
	 private UserService userService;
	 
	 @PostMapping({"/createNewRole"})
	public Role createNewRole(@RequestBody Role role) {
		 return roleservice.createNewRole(role);
		
		 
		
	}
	 
	 @PostConstruct
	 public void initRolesAnduser() {
		 userService.initRolesAndUser();
	 }

}
