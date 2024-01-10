package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.AntecedentMedicaux;

import java.util.List;

public interface AntecedentMedicauxService {

    void saveAntecedentMedicaux(AntecedentMedicaux antecedentMedicaux);

    void updateAntecedentMedicaux(Long id, AntecedentMedicaux antecedentMedicaux) throws Exception;

    AntecedentMedicaux findById(Long id);

    List<AntecedentMedicaux> findAllAntecedentMedicauxByPatient(String indexPatient);

    void deleteAntecedentMedicaux(Long id);
}
