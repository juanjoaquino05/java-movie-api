package com.applaudostudios.interview.service;

import com.applaudostudios.interview.controller.response.PaginatedMovieResponse;
import com.applaudostudios.interview.exception.MovieCantBeRentException;
import com.applaudostudios.interview.exception.MovieCantBeSoldException;
import com.applaudostudios.interview.model.Movie;
import com.applaudostudios.interview.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MovieService {
    @Autowired MovieRepository movieRepository;

    public PaginatedMovieResponse getMovies(boolean available, String title, Pageable pageable) {
        Page<Movie> movies;
        if(available){
            movies = movieRepository.findAllByAvailableAndTitleContains(available, title, pageable);
        }else{
            movies = movieRepository.findAllByTitleContains(title, pageable);
        }
        return PaginatedMovieResponse.builder()
                .content(movies.getContent())
                .size(movies.getSize())
                .numberOfElements(movies.getTotalElements())
                .totalElements(movies.getTotalElements())
                .totalPages(movies.getTotalPages())
                .number(movies.getNumber())
                .build();
    }

    public void canBeSold(Movie movie)
        throws MovieCantBeSoldException{

        boolean inStock = movie.getStock() > 0;

        if (!movie.getAvailable() || !inStock){
            throw new MovieCantBeSoldException();
        }
    }

    public void canBeRent(Movie movie)
        throws MovieCantBeRentException{

        boolean inStock = movie.getStock() > 0;

        if (!movie.getAvailable() || !inStock){
            throw new MovieCantBeRentException();
        }
    }

    public void movieSold(Movie movie) {
        movie.setStock(movie.getStock() - 1);
        movieRepository.save(movie);
    }

    public void movieRented(Movie movie) {
        movie.setStock(movie.getStock() - 1);
        movieRepository.save(movie);
    }
}
