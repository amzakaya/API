package com.example.reservation.repository;

import com.example.reservation.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a " +
           "JOIN FETCH a.user " +
           "JOIN FETCH a.business " +
           "WHERE a.id = :id")
    Optional<Appointment> findByIdWithRelations(Long id);
}
