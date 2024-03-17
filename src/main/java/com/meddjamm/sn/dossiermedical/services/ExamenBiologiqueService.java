package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.ExamenBiologique;

import java.util.List;

public interface ExamenBiologiqueService {

    ExamenBiologique saveExamenBiologique(ExamenBiologique examen);

    ExamenBiologique updateExamenBiologique(Long id, ExamenBiologique examen) throws Exception;

    ExamenBiologique findById(Long id);

    List<ExamenBiologique> findAllExamenBiologiques();

    void deleteExamenBiologique(Long id);

    List<ExamenBiologique> findExamenBiologiqueByPatientId(String code);
}
