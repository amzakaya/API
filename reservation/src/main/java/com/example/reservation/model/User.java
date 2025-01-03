package com.example.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore // Şifreyi JSON'da hariç tut
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column
    private String email;
}
