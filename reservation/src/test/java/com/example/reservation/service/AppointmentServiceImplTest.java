package com.example.reservation.service;

import com.example.reservation.model.Appointment;
import com.example.reservation.model.Business;
import com.example.reservation.model.User;
import com.example.reservation.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AppointmentServiceImplTest {

    private AppointmentRepository appointmentRepository;
    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    void setUp() {
        // Mock repository
        appointmentRepository = Mockito.mock(AppointmentRepository.class);
        appointmentService = new AppointmentServiceImpl(appointmentRepository);
    }

    @Test
    void testSaveAppointment() {
        // Mock User
        User user = new User();
        user.setId(1L);
        user.setUsername("mockUser");

        // Mock Business
        Business business = new Business();
        business.setId(1L);
        business.setName("Mock Business");

        // Mock Appointment
        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setBusiness(business);
        appointment.setDateTime(LocalDateTime.now());
        appointment.setStatus("Beklemede");

        // Mock repository behavior for save
        when(appointmentRepository.save(any(Appointment.class))).thenAnswer(invocation -> {
            Appointment savedAppointment = invocation.getArgument(0);
            savedAppointment.setId(1L); // Mock ID for saved appointment
            return savedAppointment;
        });

        // Mock repository behavior for findByIdWithRelations
        when(appointmentRepository.findByIdWithRelations(1L)).thenReturn(Optional.of(appointment));

        // Test saveAppointment
        Appointment savedAppointment = appointmentService.saveAppointment(appointment);

        // Validate the result
        assertNotNull(savedAppointment, "Saved appointment should not be null");
        assertNotNull(savedAppointment.getId(), "Saved appointment ID should not be null");
        assertEquals("mockUser", savedAppointment.getUser().getUsername(), "User username should match");
        assertEquals("Mock Business", savedAppointment.getBusiness().getName(), "Business name should match");
    }
}
