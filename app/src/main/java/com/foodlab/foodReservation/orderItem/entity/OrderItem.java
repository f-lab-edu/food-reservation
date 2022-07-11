package com.foodlab.foodReservation.orderItem.entity;

import com.foodlab.foodReservation.item.entity.Item;
import com.foodlab.foodReservation.order.entity.Orders;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private Orders order;

}
