package com.moviebookingAuth.authorizationService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingAuth.authorizationService.model.Role;
import com.moviebookingAuth.authorizationService.repository.RoleDao;



@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao;
	public Role createNewRole(Role role) {
		return roleDao.save(role);
	}

}