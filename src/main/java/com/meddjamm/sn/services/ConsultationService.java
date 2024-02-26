package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.Consultation;

import java.util.List;

public interface ConsultationService {

    void saveConsultation(Consultation consultation);

    void updateConsultation(Long id, Consultation consultation) throws Exception;

    Consultation findById(Long id);

    List<Consultation> findAllConsultations();

    void deleteConsultation(Long id);
}
