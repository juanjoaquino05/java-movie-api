package com.applaudostudios.interview.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString(callSuper=true, includeFieldNames=true)

@Entity(name = "Sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "movieId")
    private Long movieId;

    @Column(name = "customerEmail")
    private String customerEmail;

    @Column(name = "price")
    private Double price;

}
