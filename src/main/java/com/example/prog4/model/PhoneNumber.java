package com.example.prog4.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "phone_number")
@Data
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String phoneNumbers;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
