package com.foodlab.foodReservation.OrderItem.entity;

import com.foodlab.foodReservation.Item.entity.Item;
import com.foodlab.foodReservation.Order.entity.Order;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
