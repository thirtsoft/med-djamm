package com.meddjamm.sn.rh.services;

import com.meddjamm.sn.rh.entity.CodagePatient;

import java.util.List;

public interface CodagePatientService {

    Long saveCodagePatient(CodagePatient CodagePatient);

    Long updateCodagePatient(Long id, CodagePatient CodagePatient) throws Exception;

    CodagePatient findById(Long id);

    List<CodagePatient> findAllCodagePatients();

    void deleteCodagePatient(Long id);
}
