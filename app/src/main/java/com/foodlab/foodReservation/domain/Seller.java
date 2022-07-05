package com.foodlab.foodReservation.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller {

    @Id
    @GeneratedValue
    private Long id;

    private String emailAddress;

    @OneToMany(mappedBy = "seller")
    private List<Store> stores = new ArrayList<>();

}
