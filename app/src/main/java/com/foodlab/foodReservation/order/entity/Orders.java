package com.foodlab.foodReservation.order.entity;

import com.foodlab.foodReservation.common.OrderStatus;
import com.foodlab.foodReservation.customer.entity.Customer;
import com.foodlab.foodReservation.store.entity.Store;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private int price;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

}
