package com.foodlab.foodReservation.Store.repository;

import com.foodlab.foodReservation.Store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
