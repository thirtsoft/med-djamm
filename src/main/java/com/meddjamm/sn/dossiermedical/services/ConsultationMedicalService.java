package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.ConsultationMedical;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ConsultationMedicalService {

    ConsultationMedical saveConsultationMedical(ConsultationMedical consultationMedical);

    ConsultationMedical updateConsultationMedical(Long id, ConsultationMedical consultationMedical) throws Exception;

    ConsultationMedical findById(Long id);

    List<ConsultationMedical> findAllConsultationMedicals();

    void deleteConsultationMedical(Long id);

    List<ConsultationMedical> findConsultationMedicalByPatientId(String code);

    boolean addConsultationBiologicToConsultation(Long biologicId, MultipartFile biologic) throws Exception;

    boolean addConsultationImmunologicToConsultation(Long biologicId, MultipartFile immunologic) throws Exception;

    boolean addConsultationImagerToConsultation(Long biologicId, MultipartFile imager) throws Exception;

    boolean addConsultationHematologicToConsultation(Long biologicId, MultipartFile hematologic) throws Exception;

    List<ConsultationMedical> findConsultationMedicalByCircuitId(Long circuitId);

}
