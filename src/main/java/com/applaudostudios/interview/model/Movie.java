package com.applaudostudios.interview.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString(callSuper=true, includeFieldNames=true)

@Entity(name = "Movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GenericGenerator(name = "native", strategy = "native")
    @Column(name = "movieId")
    private Long movieId;

    @NotBlank(message = "Title is mandatory")
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @NotNull(message = "Stock is mandatory")
    @Column(name = "stock")
    private Integer stock;

    @NotNull(message = "Rental Price is mandatory")
    @Column(name = "rentalPrice")
    private Double rentalPrice;

    @NotNull(message = "Sale Price is mandatory")
    @Column(name = "salePrice")
    private Double salePrice;

    @Column(name = "available")
    private Boolean available;

    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL)
    private Set<Sale> sales = new HashSet<>();

    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL)
    private Set<Rental> rentals = new HashSet<>();
}
