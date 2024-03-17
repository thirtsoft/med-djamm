package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.Synthese;

import java.util.List;

public interface SyntheseService {

    Synthese saveSynthese(Synthese synthese);

    Synthese updateSynthese(Long id, Synthese synthese) throws Exception;

    Synthese findById(Long id);

    List<Synthese> findAllSyntheses();

    void deleteSynthese(Long id);

    List<Synthese> findSynthesesByPatientId(String code);
}
