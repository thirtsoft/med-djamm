package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.Medecin;

import java.util.List;

public interface MedecinService {

    Medecin saveMedecin(Medecin medecin);

    Medecin updateMedecin(Long id, Medecin medecin) throws Exception;

    Medecin findById(Long id);

    List<Medecin> findAllMedecins();

    void deleteMedecin(Long id);
}
