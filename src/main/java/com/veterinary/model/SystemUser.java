package com.veterinary.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "system_users")
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, length = 32)
    private Integer id;

    @Column(name = "email", nullable = false, length = 100, unique = true, updatable = false)
    private String email;

    @Column(name = "passwords", nullable = false, length = 50)
    private String password;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private SystemRole role;

    //---------------------
}
