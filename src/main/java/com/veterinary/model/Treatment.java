package com.veterinary.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "treatments")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_id", nullable = false, length = 32)
    private Integer id;

    @Column(name = "treatment_name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "frequency", nullable = false, length = 50)
    private String frequency;

    @Column(name = "side_effects", nullable = false, length = 100)
    private String sideEffects;

    @Column(name = "active")
    private Boolean active;
}