package com.applaudostudios.interview.controller;

import com.applaudostudios.interview.controller.response.PaginatedMovieResponse;
import com.applaudostudios.interview.exception.ElementNotFoundException;
import com.applaudostudios.interview.model.Movie;
import com.applaudostudios.interview.repository.MovieRepository;
import com.applaudostudios.interview.service.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    Logger logger = LogManager.getLogger(MovieController.class);

    @GetMapping
    public PaginatedMovieResponse getMoviesByTitleAndAvailability(
            @RequestParam(value = "title", required = false) String titleParam,
            @RequestParam(value = "unavailable", required = false) boolean unavailable,
            @PageableDefault(sort = "title", value = 12 , direction = Sort.Direction.ASC) Pageable pageable
    ) {
        String title = titleParam != null ? titleParam : "";
        boolean available = !unavailable;

        PaginatedMovieResponse movies = null;

        movies = movieService.getMovies(available, title, pageable);

        return movies;
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovie(@PathVariable(value = "movieId") Long movieId) {

        Movie movie = movieRepository.findById(movieId)
            .orElseThrow(() -> new ElementNotFoundException());

        return ResponseEntity.ok().body(movie);
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie movie) {
        Movie newMovie = movieRepository.save(movie);

        logger.info("inserted :: " + movie);

        return ResponseEntity.status(201).body(newMovie);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<Movie> editMovie(@Valid @RequestBody Movie movie, @PathVariable(value = "movieId") Long movieId) {
        Movie oldMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ElementNotFoundException());

        logger.info("Updated :: ");
        logger.info("old :: " + oldMovie);

        oldMovie.setTitle(movie.getTitle());
        oldMovie.setDescription(movie.getDescription());
        oldMovie.setStock(movie.getStock());
        oldMovie.setRentalPrice(movie.getRentalPrice());
        oldMovie.setSalePrice(movie.getSalePrice());
        oldMovie.setAvailable(movie.getAvailable());

        Movie modifiedMovie = movieRepository.save(oldMovie);

        logger.info("new :: " + modifiedMovie);

        return ResponseEntity.ok().body(modifiedMovie);
    }

    @PatchMapping("/{movieId}")
    public ResponseEntity<Movie> editMovie(@RequestBody Map<Object, Object> fields, @PathVariable(value = "movieId") Long movieId){
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new ElementNotFoundException());

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Movie.class, (String) key);
            field.setAccessible(true);

            ReflectionUtils.setField(field, movie, value);
        });

        Movie updatedMovie = movieRepository.save(movie);

        return ResponseEntity.ok(updatedMovie);
//        return null;
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity deleteMovie(@PathVariable(value = "movieId") Long movieId) {
        try{
            movieRepository.deleteById(movieId);

            logger.info("deleted :: " + movieId);
        } catch (Exception e){
            logger.error("Error: " + e.getMessage());
        }finally {
            return ResponseEntity.ok().build();
        }
    }
}
