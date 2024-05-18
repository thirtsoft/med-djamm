package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.Consultation;
import com.meddjamm.sn.dossiermedical.repository.CircuitPatientRepository;
import com.meddjamm.sn.dossiermedical.repository.ConsultationReppsitory;
import com.meddjamm.sn.dossiermedical.services.ConsultationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationReppsitory consultationRepository;

    private final CircuitPatientRepository circuitPatientRepository;

    public ConsultationServiceImpl(ConsultationReppsitory consultationRepository,
                                   CircuitPatientRepository circuitPatientRepository) {
        this.consultationRepository = consultationRepository;
        this.circuitPatientRepository = circuitPatientRepository;
    }


    @Override
    public Consultation saveConsultation(Consultation consultation) {
        consultation.setCreatedDate(new Date());
        return consultationRepository.save(consultation);
    }

    @Override
    public Consultation updateConsultation(Long id, Consultation consultation) throws Exception {
        if (!consultationRepository.existsById(id)) {
            throw new Exception("Consultat that id is " + id + "is not found");
        }
        consultation.setId(id);
        return consultationRepository.save(consultation);
    }

    @Override
    public Consultation findById(Long id) {
        return consultationRepository.findConsultationById(id);
    }

    @Override
    public List<Consultation> findAllConsultations() {
        return consultationRepository.findAllConsultations();
    }

    @Override
    public void deleteConsultation(Long id) {
        Consultation consultation = findById(id);
        consultationRepository.save(consultation);
    }

    @Override
    public List<Consultation> findConsultationsByPatientId(String code) {
        return consultationRepository.findConsultationByPatientId(code);
    }
}
