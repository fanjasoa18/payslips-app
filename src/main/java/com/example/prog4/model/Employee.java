package com.example.prog4.model;

import jakarta.persistence.*;
import jakarta.persistence.metamodel.SetAttribute;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String employeeMatricule;
    @Column(name = "image")
    private String image;
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @JoinColumn(name = "employee_id")
    private String phoneNumbers;
    private String address;
    private String emailPerso;
    private String emailPro;
    private Long cinNumber;
    private LocalDate delivranceDate;
    private String delivrancePlace;
    private String function;
    private int numberOfKids;
    private LocalDate hireDate;
    private LocalDate departureDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "csp")
    private Csp csp;
    private String cnaps;

}


