package com.foodlab.foodReservation.customer.entity;

import com.foodlab.foodReservation.order.entity.Orders;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orderList = new ArrayList<>();

}
