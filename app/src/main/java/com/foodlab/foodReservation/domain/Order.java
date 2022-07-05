package com.foodlab.foodReservation.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
