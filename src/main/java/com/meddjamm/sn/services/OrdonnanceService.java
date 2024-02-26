package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.Ordonnance;

import java.util.List;

public interface OrdonnanceService {

    Ordonnance saveOrdonnance(Ordonnance ordonnance);

    Ordonnance updateOrdonnance(Long id, Ordonnance ordonnance) throws Exception;

    Ordonnance findById(Long id);

    List<Ordonnance> findAllOrdonnances();

    void deleteOrdonnance(Long id);
}
