package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.HistoireMaladie;

import java.util.List;

public interface HistoireMaladieService {

    void saveHistoireMaladie(HistoireMaladie histoireMaladie);

    void updateHistoireMaladie(Long id, HistoireMaladie histoireMaladie) throws Exception;

    HistoireMaladie findById(Long id);
    
    List<HistoireMaladie> findHistoireMaladiesByPatient(String indexPatient);

    void deleteHistoireMaladie(Long id);
}
