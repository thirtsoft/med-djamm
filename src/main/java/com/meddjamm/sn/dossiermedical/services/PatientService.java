package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PatientService {

    Patient savePatient(Patient patient) throws Exception;

    Patient updatePatient(Long id, Patient patient) throws Exception;

    void updatePatientByMedeccin(Long id, Patient patient) throws Exception;

    Patient findById(Long id);

    Patient findByCode(String code);

    List<Patient> findAllPatients();

    void deletePatient(Long id);

    Page<Patient> findAllPatients(Integer pageNumber, Integer pageSize);

    List<Patient> findAllActivesPatients();

    long countNumberOfPatient();

    long countNumberPassagePatient(String code);

    long countNumberConsultationMedicalByPatient(String code);

    long countNumberHospitalisationByPatient(String code);

    int nombrePassage(String code);
}
