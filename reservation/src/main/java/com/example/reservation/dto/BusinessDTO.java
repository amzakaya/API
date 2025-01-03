package com.example.reservation.dto;

import lombok.Data;

@Data
public class BusinessDTO {
    private Long id;
    private String name;
    private String address;
    private String category;
    private Long ownerId;
    private String ownerUsername;
    private String ownerEmail;
}
