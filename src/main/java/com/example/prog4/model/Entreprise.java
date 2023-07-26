package com.example.prog4.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Entreprise {
    @Value("${entreprise.name}")
    private String name;
    @Value("${entreprise.description}")
    private String description;
    @Value("${entreprise.slogan}")
    private String slogan;
    @Value("${entreprise.address}")
    private String address;
    @Value("${entreprise.email}")
    private String email;
    @Value("${entreprise.phonenumber}")
    private List<String> phonenumber;
    @Value("${entreprise.nif}")
    private String nif;
    @Value("${entreprise.stat}")
    private String stat;
    @Value("${entreprise.rcs}")
    private String rcs;
    @Value("${entreprise.logo}")
    private String logo;
}
