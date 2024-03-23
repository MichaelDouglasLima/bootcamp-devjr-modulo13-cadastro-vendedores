package com.abutua.sellerbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.sellerbackend.models.Seller;
import com.abutua.sellerbackend.resources.SellerRepository;

@Service
public class SellerService {
    
    @Autowired
    private SellerRepository sellerRepository;

    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    public Seller getById(long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seller not found"));
        
        return seller;
    }

    public List<Seller> getAll() {
        return sellerRepository.findAll();
    }

    public void update(@PathVariable long id, @RequestBody Seller sellerUpdate) {
        Seller seller = getById(id);
        seller.setName(sellerUpdate.getName());
        seller.setSalary(sellerUpdate.getSalary());
        seller.setBonus(sellerUpdate.getBonus());
        seller.setGender(sellerUpdate.getGender());
        sellerRepository.save(seller);
    }

    public void deleteById(long id) {
        Seller seller = getById(id);
        sellerRepository.delete(seller);
    }

}
