package com.example.reservation.controller;

import com.example.reservation.dto.AppointmentDTO;
import com.example.reservation.model.Appointment;
import com.example.reservation.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody Appointment appointment) {
        System.out.println("User: " + appointment.getUser());
        System.out.println("Business: " + appointment.getBusiness());

        Appointment savedAppointment = appointmentService.saveAppointment(appointment);
        AppointmentDTO dto = convertToDTO(savedAppointment);

        return ResponseEntity.ok(dto);
    }



    private AppointmentDTO convertToDTO(Appointment appointment) {
        System.out.println("Mapping to DTO - Appointment ID: " + appointment.getId());
        System.out.println("Mapping to DTO - User: " + (appointment.getUser() != null ? appointment.getUser() : "null"));
        System.out.println("Mapping to DTO - Business: " + (appointment.getBusiness() != null ? appointment.getBusiness() : "null"));

        return AppointmentDTO.builder()
                .id(appointment.getId())
                .userUsername(appointment.getUser() != null && appointment.getUser().getUsername() != null 
                              ? appointment.getUser().getUsername() 
                              : "Kullanıcı bulunamadı")
                .businessName(appointment.getBusiness() != null && appointment.getBusiness().getName() != null 
                              ? appointment.getBusiness().getName() 
                              : "İşletme bulunamadı")
                .dateTime(appointment.getDateTime())
                .status(appointment.getStatus())
                .build();
    }

}
