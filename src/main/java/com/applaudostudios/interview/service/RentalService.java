package com.applaudostudios.interview.service;

import com.applaudostudios.interview.exception.ElementNotFoundException;
import com.applaudostudios.interview.exception.MovieCantBeRentException;
import com.applaudostudios.interview.exception.MovieCantBeSoldException;
import com.applaudostudios.interview.model.Movie;
import com.applaudostudios.interview.model.Rental;
import com.applaudostudios.interview.model.Sale;
import com.applaudostudios.interview.repository.MovieRepository;
import com.applaudostudios.interview.repository.RentalRepository;
import com.applaudostudios.interview.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalService {
    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RentalRepository rentalRepository;

    public Rental rentMovie(Rental rental)
        throws ElementNotFoundException, MovieCantBeRentException {
        Movie movie = movieRepository.findById(rental.getMovieId())
                .orElseThrow(() -> new ElementNotFoundException());

        movieService.canBeRent(movie);

        rental.setPrice(movie.getRentalPrice());
        Rental newRental = rentalRepository.save(rental);

        movieService.movieRented(movie);

        return newRental;
    }
}
