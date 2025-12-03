package com.veterinary.model;

import java.time.LocalDateTime;
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
import jakarta.persistence.OneToOne;
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
    @Column(name = "appointment_id")
    private Integer id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "appointment_date")
    private LocalDateTime date;

    @Column(name = "reason", length = 100)
    private String reason;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "pet_id", nullable = false)
    private Integer petId;

    @Column(name = "owner_id", nullable = false)
    private Integer ownerId;

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

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private AppointmentCancel cancellation;

}
