package com.project.crm.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class InteractionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String notes;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Getters and Setters
}
