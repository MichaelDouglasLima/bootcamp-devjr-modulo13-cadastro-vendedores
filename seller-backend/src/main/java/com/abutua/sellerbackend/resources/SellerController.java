package com.abutua.sellerbackend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abutua.sellerbackend.models.Seller;
import com.abutua.sellerbackend.services.SellerService;

@RestController
@CrossOrigin
public class SellerController {
    
    @Autowired
    private SellerService sellerService;

    @PostMapping("sellers")
    public ResponseEntity<Seller> save(@RequestBody Seller seller) {
        
        seller = sellerService.save(seller);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(seller.getId())
                .toUri();

        return ResponseEntity.created(location).body(seller);
    }

    @GetMapping("sellers/{id}")
    public ResponseEntity<Seller> getSeller(@PathVariable long id) {
        Seller seller = sellerService.getById(id);
        return ResponseEntity.ok(seller);
    }

    @GetMapping("sellers")
    public ResponseEntity<List<Seller>> getCategories() {
        return ResponseEntity.ok(sellerService.getAll());
    }

    @PutMapping("sellers/{id}")
    public ResponseEntity<Void> updateSeller(@PathVariable long id, @RequestBody Seller sellerUpdate) {
        sellerService.update(id, sellerUpdate);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("sellers/{id}")
    public ResponseEntity<Void> removeSeller(@PathVariable long id) {
        sellerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
