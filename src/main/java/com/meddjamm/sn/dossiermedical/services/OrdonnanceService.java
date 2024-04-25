package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.Ordonnance;
import com.meddjamm.sn.dossiermedical.entity.OrdonnanceItem;

import java.util.List;

public interface OrdonnanceService {

    Ordonnance saveOrdonnance(Ordonnance ordonnance);

    Ordonnance updateOrdonnance(Long id, Ordonnance ordonnance) throws Exception;

    Ordonnance findById(Long id);

    List<Ordonnance> findAllOrdonnances();

    void deleteOrdonnance(Long id);

    List<Ordonnance> findOrdonnancesByPatientId(String code);

    OrdonnanceItem findOrdonnanceItemById(Long id);

    List<Ordonnance> findOrdonnancesByCircuitId(Long circuitId);
}
