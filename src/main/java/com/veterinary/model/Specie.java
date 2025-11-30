package com.veterinary.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "species")
public class Specie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specie_id", nullable = false)
    private Integer id;

    @Column(name = "specie_name", nullable = false, length = 50)
    private String name;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "specie", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Breed> breeds = new ArrayList<>();

    public void addBreed(Breed breed) {
        breeds.add(breed);
        breed.setSpecie(this);
    }

    @OneToMany(mappedBy = "specie", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Pet> pets = new ArrayList<>();

    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setSpecie(this);
    }
}
