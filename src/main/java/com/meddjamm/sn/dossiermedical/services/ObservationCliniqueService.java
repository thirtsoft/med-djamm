package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.ObservationClinique;

import java.util.List;

public interface ObservationCliniqueService {

    void saveObservationClinique(ObservationClinique observationClinique);

    void updateObservationClinique(Long id, ObservationClinique observationClinique) throws Exception;

    ObservationClinique findById(Long id);

    List<ObservationClinique> findAllObservationCliniques();

    void deleteObservationClinique(Long id);

    List<ObservationClinique> findObservationCliniqueByPatientId(String code);
}
