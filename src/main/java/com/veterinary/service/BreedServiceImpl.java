package com.veterinary.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.veterinary.dto.request.BreedRequest;
import com.veterinary.dto.response.BreedResponse;
import com.veterinary.mapper.BreedMapper;
import com.veterinary.model.Breed;
import com.veterinary.model.Specie;
import com.veterinary.repository.BreedRepository;
import com.veterinary.repository.SpecieRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class BreedServiceImpl implements BreedService {

    private final BreedRepository breedRepository;
    private final SpecieRepository specieRepository;

    @Override
    public List<BreedResponse> getBreedByName(String name, int page, int pageSize) {
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page=" + page + ", pageSize=" + pageSize);
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Breed> breeds = breedRepository.findByName(name, pageRequest);
        return breeds.getContent().stream()
                .map(BreedMapper::toResponse)
                .toList();
    }

    @Override
    public BreedResponse findById(Integer breedId) {
        Breed breed = breedRepository.findById(breedId)
                .orElseThrow(() -> new EntityNotFoundException("Breed not found: " + breedId));
        return BreedMapper.toResponse(breed);
    }

    @Override
    public BreedResponse create(BreedRequest request) {
        Specie species = specieRepository.findById(request.getSpecieId())
                .orElseThrow(() -> new EntityNotFoundException("Species not found: " + request.getSpecieId()));
        Breed saved = breedRepository.save(BreedMapper.toEntity(request, species));
        return BreedMapper.toResponse(saved);
    }

    @Override
    public BreedResponse update(Integer breedId, BreedRequest request) {
        Breed existing = breedRepository.findById(breedId)
                .orElseThrow(() -> new EntityNotFoundException("Breed not found: " + breedId));
        Specie specie = specieRepository.findById(request.getSpecieId())
                .orElseThrow(() -> new EntityNotFoundException("Species not found: " + request.getSpecieId()));
        BreedMapper.copyToEntity(request, existing, specie);
        Breed saved = breedRepository.save(existing);
        return BreedMapper.toResponse(saved);
    }

    @Override
    public List<BreedResponse> getBreedByName(String breedName) {
        return breedRepository.getBreedsByName(breedName).stream()
                .map(BreedMapper::toResponse)
                .toList();
    }

    @Override
    public List<BreedResponse> getBreedById(Integer breedId) {
        return breedRepository.getBreedsById(breedId).stream()
                .map(BreedMapper::toResponse)
                .toList();
    }
}
