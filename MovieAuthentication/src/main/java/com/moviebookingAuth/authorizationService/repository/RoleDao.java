package com.moviebookingAuth.authorizationService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moviebookingAuth.authorizationService.model.Role;



@Repository
public interface RoleDao extends CrudRepository<Role, String>{

}

