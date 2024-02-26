package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.entity.Consultation;
import com.meddjamm.sn.repository.ConsultationRepository;
import com.meddjamm.sn.services.ConsultationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;

    public ConsultationServiceImpl(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public void saveConsultation(Consultation consultation) {
        consultation.setActif(true);
        consultation.setCreatedDate(new Date());
        consultationRepository.save(consultation);
    }

    @Override
    public void updateConsultation(Long id, Consultation consultation) throws Exception {
        if (!consultationRepository.existsById(id)) {
            new Exception("Consultat that id is " + id + "is not found");
        }
        consultation.setId(id);
        consultationRepository.save(consultation);
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
        consultation.setActif(false);
        consultationRepository.save(consultation);
    }
}
