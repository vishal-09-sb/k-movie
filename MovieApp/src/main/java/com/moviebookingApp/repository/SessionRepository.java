package com.moviebookingApp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.moviebookingApp.model.SessionDTO;

@Repository
@Transactional 
public interface SessionRepository extends JpaRepository<SessionDTO, Integer>{
	
	@Query("SELECT s FROM Session s WHERE s.sessionId = :sessionId")
	SessionDTO getLoggedSession(@Param("sessionId") Integer sessionId);

}
