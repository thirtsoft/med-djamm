package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.Patient;

import java.util.List;

public interface PatientService {

    Patient savePatient(Patient patient);

    Patient updatePatient(Long id, Patient patient) throws Exception;

    Patient findById(Long id);

    List<Patient> findAllPatients();

    void deletePatient(Long id);
}
