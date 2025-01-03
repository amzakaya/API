package com.example.reservation.controller;

import com.example.reservation.model.Business;
import com.example.reservation.service.BusinessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.reservation.dto.BusinessDTO;
import com.example.reservation.model.Business;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/businesses")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping
    public ResponseEntity<List<Business>> getAllBusinesses() {
        List<Business> businesses = businessService.getAllBusinesses();
        return ResponseEntity.ok(businesses);
    }

    @PostMapping
    public ResponseEntity<BusinessDTO> addBusiness(@RequestBody Business business) {
        Business savedBusiness = businessService.saveBusiness(business);
        BusinessDTO businessDTO = convertToDto(savedBusiness);
        return ResponseEntity.ok(businessDTO);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Business>> getBusinessesByCategory(@PathVariable String category) {
        List<Business> businesses = businessService.findBusinessesByCategory(category);
        return ResponseEntity.ok(businesses);
    }
    
    
 // İşletme Dönüştürme Metodu
    private BusinessDTO convertToDto(Business business) {
        BusinessDTO dto = new BusinessDTO();
        dto.setId(business.getId());
        dto.setName(business.getName());
        dto.setAddress(business.getAddress());
        dto.setCategory(business.getCategory());
        dto.setOwnerId(business.getOwner().getId());
        dto.setOwnerUsername(business.getOwner().getUsername());
        dto.setOwnerEmail(business.getOwner().getEmail());
        return dto;
    }
    
}
