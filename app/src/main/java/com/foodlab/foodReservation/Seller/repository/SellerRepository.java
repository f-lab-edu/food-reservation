package com.foodlab.foodReservation.Seller.repository;

import com.foodlab.foodReservation.Seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
