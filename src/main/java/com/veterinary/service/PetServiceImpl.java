package com.veterinary.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.veterinary.dto.request.PetRequest;
import com.veterinary.dto.response.PetResponse;
import com.veterinary.mapper.PetMapper;
import com.veterinary.model.Breed;
import com.veterinary.model.Owner;
import com.veterinary.model.Pet;
import com.veterinary.model.Specie;
import com.veterinary.repository.BreedRepository;
import com.veterinary.repository.OwnerRepository;
import com.veterinary.repository.PetRepository;
import com.veterinary.repository.SpecieRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class PetServiceImpl implements PetService {

    private final PetRepository repository;
    private final OwnerRepository ownerRepository;
    private final BreedRepository breedRepository;
    private final SpecieRepository specieRepository;

    @Override
    public List<PetResponse> getPetByName(String name, int page, int pageSize) {
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page=" + page + ", pageSize=" + pageSize);
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Pet> pets = repository.findByName(name, pageRequest);
        return pets.getContent().stream()
                .map(PetMapper::toResponse)
                .toList();
    }

    @Override
    public PetResponse findById(Integer id) {
        Pet pet = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found: " + id));
        return PetMapper.toResponse(pet);
    }

    @Override
    public PetResponse create(PetRequest request) {
        Owner owner = ownerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found: " + request.getOwnerId()));
        Breed breed = breedRepository.findById(request.getBreedId())
                .orElseThrow(() -> new EntityNotFoundException("Breed not found: " + request.getBreedId()));
        Specie species = specieRepository.findById(request.getSpecieId())
                .orElseThrow(() -> new EntityNotFoundException("Specie not found: " + request.getSpecieId()));
        Pet saved = repository.save(PetMapper.toEntity(request, owner, species, breed));
        return PetMapper.toResponse(saved);
    }

    @Override
    public PetResponse update(Integer id, PetRequest request) {
        Pet existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found: " + id));
        Owner owner = ownerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found. "));
        Breed breed = breedRepository.findById(request.getBreedId())
                .orElseThrow(() -> new EntityNotFoundException("Breed not found: " + request.getBreedId()));
        Specie species = specieRepository.findById(request.getSpecieId())
                .orElseThrow(() -> new EntityNotFoundException("Specie not found: " + request.getSpecieId()));
        PetMapper.copyToEntity(request, existing, owner, species, breed);
        Pet saved = repository.save(existing);
        return PetMapper.toResponse(saved);
    }

    @Override
    public List<PetResponse> getPetByName(String pet_name) {
        return repository.getPetsByName(pet_name).stream()
                .map(PetMapper::toResponse)
                .toList();
    }

    @Override
    public List<PetResponse> getPetById(Integer pet_id) {
        return repository.getPetsById(pet_id).stream()
                .map(PetMapper::toResponse)
                .toList();
    }

}
