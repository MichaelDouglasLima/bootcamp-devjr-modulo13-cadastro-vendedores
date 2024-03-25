package com.abutua.sellerbackend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abutua.sellerbackend.dto.SellerRequest;
import com.abutua.sellerbackend.dto.SellerResponse;
import com.abutua.sellerbackend.services.SellerService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("sellers")
public class SellerController {
    
    @Autowired
    private SellerService sellerService;

    @PostMapping
    public ResponseEntity<SellerResponse> save(@Validated @RequestBody SellerRequest sellerRequest) {
        
        SellerResponse seller = sellerService.save(sellerRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(seller.getId())
                .toUri();

        return ResponseEntity.created(location).body(seller);
    }

    @GetMapping("{id}")
    public ResponseEntity<SellerResponse> getSeller(@PathVariable long id) {
        SellerResponse seller = sellerService.getById(id);
        return ResponseEntity.ok(seller);
    }

    @GetMapping
    public ResponseEntity<List<SellerResponse>> getSellers() {
        return ResponseEntity.ok(sellerService.getAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateSeller(@PathVariable long id, @Valid @RequestBody SellerRequest sellerUpdate) {
        sellerService.update(id, sellerUpdate);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeSeller(@PathVariable long id) {
        sellerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
