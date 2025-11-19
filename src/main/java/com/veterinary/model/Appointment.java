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
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id", nullable = false, length = 32)
    private Integer id;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "appointment_date")
    private Timestamp date;

    @Column(name = "reason", nullable = false, length = 100)
    private String reason;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "owners_id", nullable = false)
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private AppointmentStatus appointmentStatus;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AppointmentNote> appointmentNotes = new ArrayList<>();

    public void addAppointmentNote(AppointmentNote appointmentNote) {
        appointmentNotes.add(appointmentNote);
        appointmentNote.setAppointment(this);
    }
}
