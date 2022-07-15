package com.foodlab.foodReservation.seller.repository;

import com.foodlab.foodReservation.seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {

}
