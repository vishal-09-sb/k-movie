package com.movie.book;
//
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.moviebookingApp.exceptions.DuplicateMovieNameException;
import com.moviebookingApp.model.Movie;
import com.moviebookingApp.repository.MovieRepository;
import com.moviebookingApp.service.MovieServiceImpl;

class MovieServiceImplTest {

    @InjectMocks
    MovieServiceImpl movieService;

    @Mock
    MovieRepository movieRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMoviesSuccess() {
        when(movieRepo.findAll()).thenReturn(Arrays.asList(new Movie(), new Movie()));

        List<Movie> movies = movieService.getAllMovies();

        assertNotNull(movies);
        assertEquals(2, movies.size());
    }

    @Test
    void testGetAllMoviesFailure() {
        when(movieRepo.findAll()).thenReturn(Arrays.asList());

        List<Movie> movies = movieService.getAllMovies();

        assertNull(movies);
    }

    @Test
    void testAddMovieSuccess() throws DuplicateMovieNameException {
        Movie movie = new Movie();
        when(movieRepo.findMovie("test", "test")).thenReturn(null);
        when(movieRepo.saveAndFlush(any(Movie.class))).thenReturn(movie);

        Movie savedMovie = movieService.addMovie(movie);

        assertNotNull(savedMovie);
    }

   

    @Test
    void testDeleteMovieSuccess() {
        Movie movie = new Movie();
        movie.setMovieId(1);
        when(movieRepo.findMovie("test", "test")).thenReturn(movie);

        assertTrue(movieService.deleteMovie("test", "test"));

        verify(movieRepo, times(1)).deleteById(1);
    }

    @Test
    void testDeleteMovieFailure() {
        when(movieRepo.findMovie("test", "test")).thenReturn(null);

        assertFalse(movieService.deleteMovie("test", "test"));
    }

  
}
