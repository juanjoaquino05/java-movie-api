package com.applaudostudios.interview.service;

import com.applaudostudios.interview.controller.response.PaginatedMovieResponse;
import com.applaudostudios.interview.exception.ElementNotFoundException;
import com.applaudostudios.interview.model.Movie;
import com.applaudostudios.interview.model.Sale;
import com.applaudostudios.interview.exception.MovieCantBeSoldException;
import com.applaudostudios.interview.repository.MovieRepository;
import com.applaudostudios.interview.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {
    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SaleRepository saleRepository;

    public Sale createSale(Sale sale)
        throws ElementNotFoundException, MovieCantBeSoldException{
        Movie movie = movieRepository.findById(sale.getMovieId())
                .orElseThrow(() -> new ElementNotFoundException());

        movieService.canBeSold(movie);

        sale.setPrice(movie.getSalePrice());
        Sale newSale = saleRepository.save(sale);

        movieService.movieSold(movie);

        return newSale;
    }
}
