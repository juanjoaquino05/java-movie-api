package com.applaudostudios.interview.repository;

import com.applaudostudios.interview.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findAllByAvailableAndTitleContains(Boolean available, String title, Pageable pageable);
    Page<Movie> findAllByTitleContains(String title, Pageable pageable);
    Page<Movie> findAllByAvailable(Boolean availability, Pageable pageable);
}
