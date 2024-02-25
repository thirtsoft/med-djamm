package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.DossierPatient;

import java.util.List;

public interface DossierPatientService {

    void saveDossierPatient(DossierPatient dossierPatient);

    void updateDossierPatient(Long id, DossierPatient dossierPatient) throws Exception;

    DossierPatient findById(Long id);

    List<DossierPatient> findAllDossierPatients();

    List<DossierPatient> findDossierPatientsByPatient(String indexPatient);

    void deleteDossierPatient(Long id);
}
