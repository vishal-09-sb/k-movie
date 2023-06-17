package com.moviebookingAuth.authorizationService.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviebookingAuth.authorizationService.model.User;


@Repository
public interface UserDao extends JpaRepository<User, String>{
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
	User findUserByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.userName = :userName")
	User findByUserName(@Param("userName") String userName);
	
	

}