package com.foodlab.foodReservation.Item.entity;

import com.foodlab.foodReservation.Store.entity.Store;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
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

    private boolean deleted;

    /** 연관관계 메서드 **/
    public void setStore(Store store) {
        this.store = store;
        store.getItems().add(this);
    }

    /** 생성자 **/
    public static Item createItem(String name, String description, Store store) {
        Item item = new Item();
        item.name = name;
        item.description = description;
        item.setStore(store);
        item.deleted = false;
        return item;
    }

    /** 비즈니스 메서드 **/
    public void delete() {
        this.deleted = true;
    }
}
