package com.applaudostudios.interview.repository;

import com.applaudostudios.interview.model.Rental;
import com.applaudostudios.interview.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository  extends JpaRepository<Rental, Long> {
}
