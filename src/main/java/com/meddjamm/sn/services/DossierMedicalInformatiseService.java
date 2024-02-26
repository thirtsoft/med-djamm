package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.DossierMedicalInformatise;

import java.util.List;

public interface DossierMedicalInformatiseService {

    void saveDossierMedicalInformatise(DossierMedicalInformatise dossierMedicalInformatise);

    void updateDossierMedicalInformatise(Long id, DossierMedicalInformatise dossierMedicalInformatise) throws Exception;

    DossierMedicalInformatise findById(Long id);

    DossierMedicalInformatise findByNumero(int numero);

    List<DossierMedicalInformatise> findAllDossierMedicalInformatises();

    List<DossierMedicalInformatise> findDossierMedicalInformatisesByPatient(String code);

    void deleteDossierMedicalInformatise(Long id);
}
