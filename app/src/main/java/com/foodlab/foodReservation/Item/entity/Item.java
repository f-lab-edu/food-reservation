package com.foodlab.foodReservation.Item.entity;

import com.foodlab.foodReservation.Store.entity.Store;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Lob
    private String description;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private boolean deleted = false;

    /** 비즈니스 메서드 **/
    public void delete() {
        this.deleted = true;
    }
}
