package com.example.reservation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AppointmentDTO {
    private Long id;
    private String userUsername;
    private String businessName;
    private LocalDateTime dateTime;
    private String status;
}
