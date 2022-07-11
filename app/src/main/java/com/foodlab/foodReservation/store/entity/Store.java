package com.foodlab.foodReservation.store.entity;


import com.foodlab.foodReservation.common.Address;
import com.foodlab.foodReservation.order.entity.Orders;
import com.foodlab.foodReservation.seller.entity.Seller;
import com.foodlab.foodReservation.store.dto.StoreUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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



    public void update(StoreUpdateDto storeUpdateDto, Address newAddress) {
        this.name = storeUpdateDto.getName();
        this.seller = storeUpdateDto.getSeller();
        this.address = newAddress;
    }

}
