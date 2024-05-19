package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.Consultation;

import java.util.List;

public interface ConsultationService {

    Consultation saveConsultation(Consultation consultation);

    Consultation updateConsultation(Long id, Consultation consultation) throws Exception;

    Consultation findById(Long id);

    List<Consultation> findAllConsultations();

    void deleteConsultation(Long id);

}
