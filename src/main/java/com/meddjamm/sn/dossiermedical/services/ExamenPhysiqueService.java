package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.ExamenPhysique;

import java.util.List;

public interface ExamenPhysiqueService {

    ExamenPhysique saveExamenPhysique(ExamenPhysique examen);

    ExamenPhysique updateExamenPhysique(Long id, ExamenPhysique examen) throws Exception;

    ExamenPhysique findById(Long id);
    
    List<ExamenPhysique> findAllExamenPhysiques();

    List<ExamenPhysique> findAllExamenPhysiqueByObservationClinique(Long observationCliniqueId);

    void deleteExamenPhysique(Long id);
}
