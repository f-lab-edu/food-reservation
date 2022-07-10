package com.foodlab.foodReservation.Store.entity;

import com.foodlab.foodReservation.Common.Address;
import com.foodlab.foodReservation.Item.entity.Item;
import com.foodlab.foodReservation.Seller.entity.Seller;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToMany(mappedBy = "store")
    private List<Item> items = new ArrayList<>();

    /** 연관관계 메서드 **/
    public void setSeller(Seller seller) {
        this.seller = seller;
        seller.getStores().add(this);
    }

    /** 생성자 **/
    public static Store createStore(String name, Address address, Seller seller) {
        Store store = new Store();
        store.name = name;
        store.address = address;
        store.setSeller(seller);
        return store;
    }
}
