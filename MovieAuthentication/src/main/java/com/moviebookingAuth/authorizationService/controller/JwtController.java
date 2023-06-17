package com.moviebookingAuth.authorizationService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import com.moviebookingAuth.authorizationService.model.JwtRequest;
import com.moviebookingAuth.authorizationService.model.JwtResponse;
import com.moviebookingAuth.authorizationService.model.User;
import com.moviebookingAuth.authorizationService.service.JwtService;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
//@RequestMapping("api/v1.0")
public class JwtController {
	@Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
    	User reqUser = new User();
    	reqUser.setUserName(jwtRequest.getUserName());
    	reqUser.setUserPassword(jwtRequest.getUserPassword());
    	JwtResponse res = jwtService.createJwtToken(jwtRequest);
    	
        return res;
    }
}