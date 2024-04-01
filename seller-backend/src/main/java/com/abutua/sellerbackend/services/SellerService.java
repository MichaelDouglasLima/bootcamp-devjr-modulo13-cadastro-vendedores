package com.abutua.sellerbackend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.sellerbackend.dto.SellerRequest;
import com.abutua.sellerbackend.dto.SellerResponse;
import com.abutua.sellerbackend.models.Seller;
import com.abutua.sellerbackend.repositories.SellerRepository;
import com.abutua.sellerbackend.services.exceptions.DatabaseException;

import jakarta.persistence.EntityNotFoundException;
// import javax.persistence.EntityNotFoundException;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public SellerResponse save(SellerRequest sellerRequest) {
        Seller seller = sellerRepository.save(sellerRequest.toEntity());
        return seller.toDTO();
    }

    public SellerResponse getById(long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seller not found"));

        return seller.toDTO();
    }

    public List<SellerResponse> getAll() {
        return sellerRepository.findAll()
                .stream()
                .map(s -> s.toDTO())
                .collect(Collectors.toList());
    }

    public void update(@PathVariable long id, @RequestBody SellerRequest sellerUpdate) {
        try {
            Seller seller = sellerRepository.getReferenceById(id);
            seller.setName(sellerUpdate.getName());
            seller.setSalary(sellerUpdate.getSalary());
            seller.setBonus(sellerUpdate.getBonus());
            seller.setGender(sellerUpdate.getGender());
            sellerRepository.save(seller);
        } 
        catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Seller not found");
        }
    }

    public void deleteById(long id) {
        try {
            if (sellerRepository.existsById(id)) {
                sellerRepository.deleteById(id);
            }
            else {
                throw new EntityNotFoundException("Seller not found");
            }
        }
        catch (DataIntegrityViolationException e) {
            throw new  DatabaseException("Conflict when removing seller");
        }
    }

}
