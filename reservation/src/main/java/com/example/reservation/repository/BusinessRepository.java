package com.example.reservation.repository;

import com.example.reservation.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    Optional<Business> findByName(String name);
    List<Business> findByCategory(String category);
}
