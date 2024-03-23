package com.abutua.sellerbackend.resources;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abutua.sellerbackend.models.Seller;

public interface SellerRepository extends JpaRepository <Seller,Long> {
    
}
