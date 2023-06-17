package com.moviebookingAuth.authorizationService.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.moviebookingAuth.authorizationService.model.ForgetPassword;
import com.moviebookingAuth.authorizationService.model.Role;
import com.moviebookingAuth.authorizationService.model.User;
import com.moviebookingAuth.authorizationService.repository.RoleDao;
import com.moviebookingAuth.authorizationService.repository.UserDao;



@Service
public class UserService {
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
  
	 
	
	public void initRolesAndUser() {
		Role adminRole=new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDesc("Admin Role");
		roleDao.save(adminRole);
		
		Role userRole=new Role();
		userRole.setRoleName("User");
		userRole.setRoleDesc("Default Role for user");
		roleDao.save(userRole);
		
		User adminUser=new User();
		adminUser.setFullName("admin");
		adminUser.setUserName("admin123");
		adminUser.setEmail("admin@gmail.com");
		adminUser.setUserPassword(getEncodedPassword("admin@123"));
		Set<Role> adminRoles=new HashSet<>();
		adminUser.setSecretQuestion("Who are you?");
		adminUser.setSecretAnswer("admin");
		adminRoles.add(adminRole);
		adminUser.setRole(adminRoles);
		userdao.save(adminUser);
		
	User user=new User();
	user.setFullName("Kousalya");
	user.setUserName("Koush");
	user.setEmail("kousalya@gmail.com");
	user.setUserPassword(getEncodedPassword("Kousalya@123"));
	Set<Role> userRoles=new HashSet<>();
	userRoles.add(userRole);
	user.setRole(userRoles);
	user.setSecretQuestion("What is your favrouite color?");
	user.setSecretAnswer("Black");
	
	userdao.save(user);
	}
	
	
	
	
	public HttpStatus registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        User savedUser =  userdao.save(user);
        
        if(savedUser!=null) {
        	return HttpStatus.OK;
        }
       
        return HttpStatus.BAD_REQUEST;

       
    }
	
	public String userNameValidator(String email, String password) {
	    User user = userdao.findUserByEmail(email);
	    
	    if(user != null) {
	        if(passwordEncoder.matches(password, user.getUserPassword())) {
	            System.out.println("Password Matched...");
	            return user.getUserName();
	        } else {
	            System.out.println("Oops..!Password is not matching");
	        }
	    } else {
	        System.out.println("No user..");
	    }

	    return null;
	}
	
	public HttpStatus updateUserPassword(ForgetPassword forgetPassword) {
		
		User user = userdao.findByUserName(forgetPassword.getUserName());
		
		if(user != null) {
			if(user.getSecretQuestion().equals(forgetPassword.getSecretQuestion())) {
				if(user.getSecretAnswer().equals(forgetPassword.getSecretAnswer())) {
					user.setUserPassword(getEncodedPassword(forgetPassword.getNewPassword()));
					userdao.save(user);
					System.out.println("Password Updated..");
					return HttpStatus.ACCEPTED;
				}else {
					System.out.println("The provided secret Answer didn't match");
				}
			}else {
				System.out.println("The provided Secret Questions didn't match");
			}
		}else {
			System.out.println("User not found with this userName");
		}
		
	
		
		return HttpStatus.NOT_MODIFIED;
	}

	
	public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
	
	public boolean isPasswordValid(String rawPassword, String hashedPassword) {
	    return passwordEncoder.matches(rawPassword, hashedPassword);
	}

}

