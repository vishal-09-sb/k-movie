package com.movie.book;


import com.moviebookingApp.controller.MovieController;

import com.moviebookingApp.model.Movie;
import com.moviebookingApp.service.MovieService;
import com.moviebookingApp.service.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MovieControllerTest {

    @Mock
    private MovieService movieService;
    
    @Mock
    private SessionService sessionService;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddMovieSuccess() throws Exception {
        Movie movie = new Movie();
        movie.setMovieName("TestMovie");

        when(sessionService.checkSessionUserType()).thenReturn("admin");
        when(movieService.addMovie(any(Movie.class))).thenReturn(movie);

        ResponseEntity<?> responseEntity = movieController.addMovie(movie);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(movie, responseEntity.getBody());
    }

    @Test
    void testAddMovieFailure() throws Exception {
        Movie movie = new Movie();
        movie.setMovieName("TestMovie");

        when(sessionService.checkSessionUserType()).thenReturn("admin");
        when(movieService.addMovie(any(Movie.class))).thenReturn(null);

        ResponseEntity<?> responseEntity = movieController.addMovie(movie);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals("No Movie", responseEntity.getBody());
    }

    @Test
    void testAddMovieException() {
        Movie movie = new Movie();
        movie.setMovieName("TestMovie");

        assertThrows(Exception.class, () -> movieController.addMovie(movie));
    }
}
