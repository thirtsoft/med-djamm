package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.ExamenComplementaire;

import java.util.List;

public interface ExamenComplementaireService {

    ExamenComplementaire saveExamenComplementaire(ExamenComplementaire examen);

    ExamenComplementaire updateExamenComplementaire(Long id, ExamenComplementaire examen) throws Exception;

    ExamenComplementaire findById(Long id);

    List<ExamenComplementaire> findAllExamenComplementaires();

    void deleteExamenComplementaire(Long id);

    List<ExamenComplementaire> findExamenComplementaireByPatientId(String code);
}
