package com.moviebookingApp.service;

import java.util.List;

import com.moviebookingApp.exceptions.DuplicateMovieIdExceptions;
import com.moviebookingApp.exceptions.DuplicateMovieNameException;
import com.moviebookingApp.model.Movie;

public interface MovieService {

	public List<Movie> getAllMovies();

	public Movie addMovie(Movie movie) throws DuplicateMovieIdExceptions, DuplicateMovieNameException;

	public boolean deleteMovie(String movieName, String theatreName);

	public boolean updateMovie(Movie movie);

	public Movie getMovieById(int mid);
	
	public List<Movie> getMovieByName(String name);
	

}
