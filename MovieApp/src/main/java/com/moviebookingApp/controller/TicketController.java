package com.moviebookingApp.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingApp.model.Movie;
import com.moviebookingApp.model.Ticket;
import com.moviebookingApp.service.MovieService;
import com.moviebookingApp.service.SessionService;
import com.moviebookingApp.service.TicketService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.moviebookingapp.kafka.Producer;

@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("api/v1.0/ticket")
public class TicketController {
	
	@Autowired
	private TicketService ts;
	
	@Autowired
	private MovieService ms;
	
	@Autowired
	private SessionService sessionService;
	
//	@Autowired
//	Producer producer;
	
	Logger log = LoggerFactory.getLogger(TicketController.class);
	
	@PostMapping("/add/{movieId}/{seatsBooked}")
	public ResponseEntity<?> addTicket(@PathVariable("movieId")int movieId, @PathVariable("seatsBooked")int seatsBooked) throws Exception
	{
		
		ForUser();
		
		log.info("Attempting to add new ticket for movie ID {} with {} seats booked", movieId, seatsBooked);
		Movie m1 = ms.getMovieById(movieId);
		
		
		if(m1 !=null)
		{
			
			Ticket ticket = new Ticket();
			ticket.setMovie_id_fk(m1.getMovieId());
			ticket.setMovieName(m1.getMovieName());
			ticket.setTotalSeat(100);
			
			int seatsAvailable = m1.getSeatsAvailable();
			
			if(seatsAvailable-seatsBooked < 0) {
				log.info("Ticket cannot be created as seats requested are more than available seats");
				return new ResponseEntity<String>("Ticket cannot be created as seats you are trying to book is more than available seats", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			ticket.setSeatsAvailable(seatsAvailable - seatsBooked);
			ticket.setSeatsBooked(seatsBooked);
			
			m1.setSeatsAvailable(seatsAvailable - seatsBooked);
			m1.setBookedSeats(m1.getBookedSeats()+seatsBooked);
					
			if(ms.updateMovie(m1) && ts.addTicket(ticket))
			{
				//producer.sendMessage(ticket.getMovieName());
				log.info("Ticket added successfully for movie ID {} with {} seats booked", movieId, seatsBooked);
				return new ResponseEntity<Ticket>(ticket, HttpStatus.CREATED);
			}
		}
		log.info("Failed to add ticket for movie ID {} with {} seats booked", movieId, seatsBooked);
		return new ResponseEntity<String>("Ticket cannot be created", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping("/admin/getAllTickets")
	public ResponseEntity<?> getAllTickets() throws Exception 
	{
		ForAdmin();
		
		log.info("Fetching all tickets");
		
		List<Ticket> ticketList = ts.getAllTickets();
		
		log.info("Fetched {} tickets", ticketList != null ? ticketList.size() : 0);
		if(ticketList!=null && !ticketList.isEmpty())
		{
			

			return new ResponseEntity<List<Ticket>>(ticketList, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Ticket list is Empty", HttpStatus.NO_CONTENT);
		
	}
	
	public void ForAdmin() throws Exception {
		String sessionUserType =  sessionService.checkSessionUserType();
		
		System.out.println("LoggedSessionUserType is : " + sessionUserType);
		
		if(!sessionUserType.equals("admin")) {
			System.out.println("Oops !! This method is only for admin!!");
			throw new Exception("This Method is only reserved for admin usertype!!");
		}
	}
	
	public void ForUser() throws Exception {
		String sessionUserType =  sessionService.checkSessionUserType();
		
		System.out.println("LoggedSessionUserType is : " + sessionUserType);
		
		if(!sessionUserType.equals("user")) {
			System.out.println("Oops !! This method is only for user type!!");
			throw new Exception("This Method is only reserved for user type users!!");
		}
	}

}
