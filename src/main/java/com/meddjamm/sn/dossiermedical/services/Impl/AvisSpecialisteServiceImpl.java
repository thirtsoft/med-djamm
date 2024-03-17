package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.AvisSpecialiste;
import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import com.meddjamm.sn.dossiermedical.repository.AvisSpecialisteRepository;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.services.AvisSpecialisteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AvisSpecialisteServiceImpl implements AvisSpecialisteService {

    private final AvisSpecialisteRepository avisSpecialisteRepository;

    private final CircuitPatientRepository circuitPatientRepository;

    public AvisSpecialisteServiceImpl(AvisSpecialisteRepository avisSpecialisteRepository,
                                      CircuitPatientRepository circuitPatientRepository) {
        this.avisSpecialisteRepository = avisSpecialisteRepository;
        this.circuitPatientRepository = circuitPatientRepository;
    }

    @Override
    public AvisSpecialiste saveAvisSpecialiste(AvisSpecialiste avisSpecialiste) {
        avisSpecialiste.setActif(true);
        avisSpecialiste.setCreatedDate(new Date());
        CircuitPatient circuitPatient = circuitPatientRepository.findCircuitPatientById(avisSpecialiste.getCircuitPatientId());
        avisSpecialiste.setCircuitPatientId(circuitPatient.getId());
        avisSpecialiste.setCircuitPatient(circuitPatient);
        return avisSpecialisteRepository.save(avisSpecialiste);
    }

    @Override
    public AvisSpecialiste updateAvisSpecialiste(Long id, AvisSpecialiste avisSpecialiste) throws Exception {
        if (!avisSpecialisteRepository.existsById(id)) {
            log.info("Avis specialiste that this id " + id + "not found");
        }
        avisSpecialiste.setId(id);
        return avisSpecialisteRepository.save(avisSpecialiste);
    }

    @Override
    public AvisSpecialiste findById(Long id) {
        return avisSpecialisteRepository.findAvisSpecialisteById(id);
    }

    @Override
    public List<AvisSpecialiste> findAllAvisSpecialistes() {
        return avisSpecialisteRepository.findAllAvisSpecialistes();
    }

    @Override
    public void deleteAvisSpecialiste(Long id) {
        AvisSpecialiste avisSpecialiste = findById(id);
        avisSpecialiste.setActif(false);
        avisSpecialisteRepository.save(avisSpecialiste);
    }

    @Override
    public List<AvisSpecialiste> findAvisSpecialisteByPatientId(String code) {
        return avisSpecialisteRepository.findAvisSpecialisteByPatientId(code);
    }
}