package com.foodlab.foodReservation.order.entity;

import com.foodlab.foodReservation.customer.entity.Customer;
import com.foodlab.foodReservation.store.entity.Store;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    private int status;

    private int price;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

}
