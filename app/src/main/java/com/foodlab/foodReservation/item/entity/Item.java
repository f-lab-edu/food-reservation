package com.foodlab.foodReservation.item.entity;

import com.foodlab.foodReservation.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    private boolean deleted;

    public void setStore(Store store) {
        this.store = store;
        store.getItemList().add(this);
    }

    /*
    Item 생성자
    - Store와 양방향 연관관계를 지정하며 Item을 생성한다.
     */
    public Item(String name, int price, Store store) {
        this.name = name;
        this.price = price;
        this.setStore(store);
        this.deleted = false;
    }

    public void updateItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void delete() {
        this.deleted = true;
    }

}
