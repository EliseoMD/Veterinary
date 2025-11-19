package com.veterinary.service;

import java.util.List;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.veterinary.dto.request.ClinicalHistoryRequest;
import com.veterinary.dto.response.ClinicalHistoryResponse;
import com.veterinary.mapper.ClinicalHistoryMapper;
import com.veterinary.model.ClinicalHistory;
import com.veterinary.model.Consultation;
import com.veterinary.model.Pet;
import com.veterinary.model.Treatment;
import com.veterinary.repository.ClinicalHistoryRepository;
import com.veterinary.repository.ConsultationRepository;
import com.veterinary.repository.PetRepository;
import com.veterinary.repository.TreatmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClinicalHistoryServiceImpl implements ClinicalHistoryService {

    private final ClinicalHistoryRepository historyRepository;
    private final PetRepository petRepository;
    private final ConsultationRepository consultationRepository;
    private final TreatmentRepository treatmentRepository;

    @Override
    public List<ClinicalHistoryResponse> getClinicalHistoryByDate(Date date, int page, int pageSize) {
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page=" + page + ", pageSize=" + pageSize);
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<ClinicalHistory> histories = historyRepository.findByDate(date, pageRequest);
        return histories.getContent().stream()
                .map(ClinicalHistoryMapper::toResponse)
                .toList();
    }

    @Override
    public ClinicalHistoryResponse findById(Integer historyId) {
        ClinicalHistory clinicalHistory = historyRepository.findById(historyId)
                .orElseThrow(() -> new EntityNotFoundException("Clinical history not found: " + historyId));
        return ClinicalHistoryMapper.toResponse(clinicalHistory);
    }

    @Override
    public ClinicalHistoryResponse create(ClinicalHistoryRequest request) {
        Pet pet = petRepository.findById(request.getPetId())
                .orElseThrow(() -> new EntityNotFoundException("Pet not found."));
        Consultation consultation = consultationRepository.findById(request.getConsultationId())
                .orElseThrow(() -> new EntityNotFoundException("Consultation not found."));
        Treatment treatment = treatmentRepository.findById(request.getTreatmentId())
                .orElseThrow(() -> new EntityNotFoundException("Treatment not found."));
        ClinicalHistory saved = historyRepository.save(ClinicalHistoryMapper.toEntity(request, pet, consultation, treatment));
        return ClinicalHistoryMapper.toResponse(saved);
    }

    @Override
    public ClinicalHistoryResponse update(Integer historyId, ClinicalHistoryRequest request) {
        ClinicalHistory existing = historyRepository.findById(historyId)
                .orElseThrow(() -> new EntityNotFoundException("Clinical history not found: " + historyId));
        Pet pet = petRepository.findById(request.getPetId())
                .orElseThrow(() -> new EntityNotFoundException("Pet not found."));
        Consultation consultation = consultationRepository.findById(request.getConsultationId())
                .orElseThrow(() -> new EntityNotFoundException("Consultation not found."));
        Treatment treatment = treatmentRepository.findById(request.getTreatmentId())
                .orElseThrow(() -> new EntityNotFoundException("Treatment not found."));
        ClinicalHistoryMapper.copyToEntity(request, existing, pet, consultation, treatment);
        ClinicalHistory saved = historyRepository.save(existing);
        return ClinicalHistoryMapper.toResponse(saved);
    }

    @Override
    public List<ClinicalHistoryResponse> getClinicalHistoryById(Integer historyId) {
        return historyRepository.getClinicalHistoriesById(historyId).stream()
                .map(ClinicalHistoryMapper::toResponse)
                .toList();
    }

    @Override
    public List<ClinicalHistoryResponse> getClinicalHistoryByDate(Date date) {
        return historyRepository.getClinicalHistoriesByDate(date).stream()
                .map(ClinicalHistoryMapper::toResponse)
                .toList();
    }
}
