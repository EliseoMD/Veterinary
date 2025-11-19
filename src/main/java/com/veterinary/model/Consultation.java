package com.veterinary.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultation_id", nullable = false, length = 32)
    private Integer id;

    @Column(name = "appointment_type", nullable = false, length = 20)
    private String type;

    @Column(name = "diagnosis", nullable = false, length = 200)
    private String diagnosis;

    @Column(name = "consultation_date")
    private Timestamp date;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "veterinarian_id", nullable = false)
    private Veterinarian veterinarian;

    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ClinicalHistory> clinicalHistories = new ArrayList<>();

    public void addClinicalHistory(ClinicalHistory clinicalHistory) {
        clinicalHistories.add(clinicalHistory);
        clinicalHistory.setConsultation(this);
    }
}
