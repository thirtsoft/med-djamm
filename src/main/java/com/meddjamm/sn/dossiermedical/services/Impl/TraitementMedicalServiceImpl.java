package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import com.meddjamm.sn.dossiermedical.entity.TraitementMedical;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.repository.TraitementMedicalRepository;
import com.meddjamm.sn.dossiermedical.services.TraitementMedicalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TraitementMedicalServiceImpl implements TraitementMedicalService {

    private final TraitementMedicalRepository traitementMedicalRepository;

    private final CircuitPatientRepository circuitPatientRepository;

    public TraitementMedicalServiceImpl(TraitementMedicalRepository traitementMedicalRepository,
                                        CircuitPatientRepository circuitPatientRepository) {
        this.traitementMedicalRepository = traitementMedicalRepository;
        this.circuitPatientRepository = circuitPatientRepository;
    }

    @Override
    public TraitementMedical saveTraitementMedical(TraitementMedical traitementMedical) {
        traitementMedical.setActif(true);
        traitementMedical.setCreatedDate(new Date());
        CircuitPatient circuitPatient = circuitPatientRepository.findCircuitPatientById(traitementMedical.getCircuitPatientId());
        traitementMedical.setCircuitPatientId(circuitPatient.getId());
        traitementMedical.setCircuitPatient(circuitPatient);
        return traitementMedicalRepository.save(traitementMedical);
    }

    @Override
    public TraitementMedical updateTraitementMedical(Long id, TraitementMedical traitementMedical) throws Exception {
        traitementMedical.setId(id);
        return traitementMedicalRepository.save(traitementMedical);
    }

    @Override
    public TraitementMedical findById(Long id) {
        return traitementMedicalRepository.findById(id).orElseThrow(() -> new RuntimeException("Traitement not found"));
    }

    @Override
    public List<TraitementMedical> findAllTraitementMedicals() {
        return traitementMedicalRepository.findAll();
    }

    @Override
    public void deleteTraitementMedical(Long id) {
        TraitementMedical traitementMedical = findById(id);
        traitementMedical.setActif(true);
        traitementMedicalRepository.save(
                traitementMedical
        );
    }

    @Override
    public List<TraitementMedical> findTraitementMedicalByPatientId(String code) {
        return traitementMedicalRepository.findTraitementMedicalByPatientId(code);
    }
}