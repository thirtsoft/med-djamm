package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PatientService {

    Patient savePatient(Patient patient);

    Patient updatePatient(Long id, Patient patient) throws Exception;

    Patient findById(Long id);

    Patient findByIndex(String index);

    List<Patient> findAllPatients();

    void deletePatient(Long id);

    Page<Patient> findAllPatients(Integer pageNumber, Integer pageSize);

}
