package com.example.reservation.service;



import com.example.reservation.model.Business;

import java.util.List;

public interface BusinessService {
    List<Business> getAllBusinesses();
    Business saveBusiness(Business business);
    List<Business> findBusinessesByCategory(String category);
}
