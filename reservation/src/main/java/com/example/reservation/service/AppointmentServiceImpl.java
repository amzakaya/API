package com.example.reservation.service;

import com.example.reservation.model.Appointment;
import com.example.reservation.repository.AppointmentRepository;
import com.example.reservation.service.AppointmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        System.out.println("Saving Appointment: User ID = " + appointment.getUser().getId());
        System.out.println("Saving Appointment: Business ID = " + appointment.getBusiness().getId());

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentRepository.findByIdWithRelations(savedAppointment.getId())
                .orElseThrow(() -> new RuntimeException("Appointment not found after save!"));
    }

}
