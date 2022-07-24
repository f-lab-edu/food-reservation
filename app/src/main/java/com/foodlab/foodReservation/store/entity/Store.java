package com.foodlab.foodReservation.store.entity;

import com.foodlab.foodReservation.common.Address;
import com.foodlab.foodReservation.item.entity.Item;
import com.foodlab.foodReservation.order.entity.Orders;
import com.foodlab.foodReservation.seller.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;

    @OneToMany(mappedBy = "store")
    private List<Orders> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Item> itemList = new ArrayList<>();

    private boolean deleted;

    public void update(String name, Seller seller, Address address) {
        this.name = name;
        this.seller = seller;
        this.address = address;
    }

    public Store(String name, Address address, Seller seller) {
        this.name = name;
        this.address = address;
        setSeller(seller);
        deleted = false;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
        seller.getStoreList().add(this);
    }

    public void delete() {
        this.deleted = true;
        itemList.forEach(Item::delete); // 상점에 등록된 메뉴(item)도 모두 삭제
    }
}
