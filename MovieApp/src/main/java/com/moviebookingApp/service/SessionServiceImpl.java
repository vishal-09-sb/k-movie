package com.moviebookingApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.moviebookingApp.model.SessionDTO;

import com.moviebookingApp.repository.SessionRepository;

@Service
public class SessionServiceImpl implements SessionService{
	
	Logger log = LoggerFactory.getLogger(SessionServiceImpl.class);
	
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public HttpStatus addSession(SessionDTO sessionDTO) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Deleting all sessions from DB");
		
		sessionRepository.deleteAll();
		
		System.out.println("Saving session to DB");
		sessionDTO.setSessionId(1);
		
		sessionRepository.save(sessionDTO);
		
		return HttpStatus.CREATED;
	}

	@Override
	public String checkSessionUserType() throws Exception {
		// TODO Auto-generated method stub
		
		SessionDTO session =  sessionRepository.getLoggedSession(1);
		
		if(session!=null) {
			return session.getUserType();
		}
		
		return "NoUser";
	}
	
	

}
