package com.veterinary.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.veterinary.dto.request.PetPhotoRequest;
import com.veterinary.dto.response.PetPhotoResponse;
import com.veterinary.mapper.PetPhotoMapper;
import com.veterinary.model.Pet;
import com.veterinary.model.PetPhoto;
import com.veterinary.repository.PetPhotoRepository;
import com.veterinary.repository.PetRepository; // Asumiendo que ya tienes o tendrás este repo
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetPhotoServiceImpl implements PetPhotoService {

    private final PetPhotoRepository repository;
    private final PetRepository petRepository;

    @Override
    public PetPhotoResponse findById(Integer id) {
        PetPhoto entity = repository.getPhotoById(id)
                .orElseThrow(() -> new EntityNotFoundException("Photo not found: " + id));
        return PetPhotoMapper.toResponse(entity);
    }

    @Override
    public PetPhotoResponse create(PetPhotoRequest request) {
        Pet pet = petRepository.findById(request.getPetId())
                .orElseThrow(() -> new EntityNotFoundException("Pet not found: " + request.getPetId()));

        PetPhoto entity = PetPhotoMapper.toEntity(request, pet);
        entity.setUploadDate(LocalDateTime.now());

        PetPhoto saved = repository.save(entity);
        return PetPhotoMapper.toResponse(saved);
    }

    @Override
    public PetPhotoResponse update(Integer id, PetPhotoRequest request) {
        PetPhoto existing = repository.getPhotoById(id)
                .orElseThrow(() -> new EntityNotFoundException("Photo not found: " + id));

        PetPhotoMapper.copyToEntity(request, existing);
        PetPhoto saved = repository.save(existing);
        return PetPhotoMapper.toResponse(saved);
    }

    @Override
    public List<PetPhotoResponse> getPhotosByPetId(Integer petId) {
        return repository.getPhotosByPetId(petId).stream()
                .map(PetPhotoMapper::toResponse)
                .toList();
    }

}
