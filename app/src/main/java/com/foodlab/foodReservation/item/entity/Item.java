package com.foodlab.foodReservation.item.entity;

import com.foodlab.foodReservation.store.entity.Store;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    private boolean deleted;

    public void delete() {
        this.deleted = true;
    }
}
