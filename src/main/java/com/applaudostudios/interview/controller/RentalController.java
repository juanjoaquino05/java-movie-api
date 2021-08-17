package com.applaudostudios.interview.controller;

import com.applaudostudios.interview.model.Rental;
import com.applaudostudios.interview.model.Sale;
import com.applaudostudios.interview.service.RentalService;
import com.applaudostudios.interview.service.SaleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    Logger logger = LogManager.getLogger(RentalController.class);

    @PostMapping
    public ResponseEntity<Rental> createSale(@RequestBody Rental rental) {
        Rental newRent = rentalService.rentMovie(rental);

        logger.info("inserted :: " + newRent);

        return ResponseEntity.status(201).body(newRent);
    }


}
