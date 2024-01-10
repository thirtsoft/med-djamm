package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.AntecedentGynecologie;

import java.util.List;

public interface AntecedentGynecologieService {

    void saveAntecedentGynecologie(AntecedentGynecologie antecedentGynecologie);

    void updateAntecedentGynecologie(Long id, AntecedentGynecologie antecedentGynecologie) throws Exception;

    AntecedentGynecologie findById(Long id);

    List<AntecedentGynecologie> findAllAntecedentGynecologieByPatient(String indexPatient);

    void deleteAntecedentGynecologie(Long id);
}
