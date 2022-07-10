package com.foodlab.foodReservation.Customer.entity;

import com.foodlab.foodReservation.Order.entity.Order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String emailAddress;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();
}
