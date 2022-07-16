package com.foodlab.foodReservation.orderItem.entity;

import com.foodlab.foodReservation.item.entity.Item;
import com.foodlab.foodReservation.order.entity.Orders;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


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
