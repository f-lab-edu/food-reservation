package com.foodlab.foodReservation.Seller.entity;

import com.foodlab.foodReservation.Store.entity.Store;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seller {

    @Id
    @GeneratedValue
    private Long id;

    private String emailAddress;

    @OneToMany(mappedBy = "seller")
    private List<Store> stores = new ArrayList<>();

    public static Seller createSeller(String emailAddress) {
        Seller seller = new Seller();
        seller.emailAddress = emailAddress;
        return seller;
    }

}
