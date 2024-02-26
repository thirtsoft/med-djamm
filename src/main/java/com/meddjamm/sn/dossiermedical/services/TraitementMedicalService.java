package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.TraitementMedical;

import java.util.List;

public interface TraitementMedicalService {

    TraitementMedical saveTraitementMedical(TraitementMedical traitementMedical);

    TraitementMedical updateTraitementMedical(Long id, TraitementMedical traitementMedical) throws Exception;

    TraitementMedical findById(Long id);

    List<TraitementMedical> findAllTraitementMedicals();

    void deleteTraitementMedical(Long id);

    List<TraitementMedical> findTraitementMedicalByPatientId(String code);
}
