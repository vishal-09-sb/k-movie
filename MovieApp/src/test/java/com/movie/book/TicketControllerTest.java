package com.movie.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.moviebookingApp.controller.TicketController;
import com.moviebookingApp.model.Movie;
import com.moviebookingApp.model.Ticket;
import com.moviebookingApp.service.MovieService;
import com.moviebookingApp.service.SessionService;
import com.moviebookingApp.service.TicketService;

public class TicketControllerTest {

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private MovieService movieService;

    @Mock
    private SessionService sessionService;

    @Mock
    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTicketSuccess() throws Exception {
        int movieId = 1;
        int seatsBooked = 2;

        Movie movie = new Movie();
        movie.setMovieId(movieId);
        movie.setSeatsAvailable(3);

        Ticket ticket = new Ticket();
        ticket.setMovie_id_fk(movieId);
        ticket.setSeatsBooked(seatsBooked);

        when(sessionService.checkSessionUserType()).thenReturn("user");
        when(movieService.getMovieById(movieId)).thenReturn(movie);
        when(movieService.updateMovie(any(Movie.class))).thenReturn(true);
        when(ticketService.addTicket(any(Ticket.class))).thenReturn(true); 

        ResponseEntity<?> responseEntity = ticketController.addTicket(movieId, seatsBooked);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        Ticket responseTicket = (Ticket) responseEntity.getBody();

        assertEquals(movieId, responseTicket.getMovie_id_fk());
        assertEquals(seatsBooked, responseTicket.getSeatsBooked());
    }


    @Test
    void testAddTicketFailure() throws Exception {
        int movieId = 1;
        int seatsBooked = 5;

        Movie movie = new Movie();
        movie.setMovieId(movieId);
        movie.setSeatsAvailable(3); 

        when(sessionService.checkSessionUserType()).thenReturn("user");
        when(movieService.getMovieById(movieId)).thenReturn(movie);

        ResponseEntity<?> responseEntity = ticketController.addTicket(movieId, seatsBooked);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Ticket cannot be created as seats you are trying to book is more than available seats", responseEntity.getBody());
    }
}
