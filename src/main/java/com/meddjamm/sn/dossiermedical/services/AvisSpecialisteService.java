package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.AvisSpecialiste;

import java.util.List;

public interface AvisSpecialisteService {

    AvisSpecialiste saveAvisSpecialiste(AvisSpecialiste avisSpecialiste);

    AvisSpecialiste updateAvisSpecialiste(Long id, AvisSpecialiste avisSpecialiste) throws Exception;

    AvisSpecialiste findById(Long id);

    List<AvisSpecialiste> findAllAvisSpecialistes();

    void deleteAvisSpecialiste(Long id);

    List<AvisSpecialiste> findAvisSpecialisteByPatientId(String code);

    List<AvisSpecialiste> findAvisSpecialisteByCircuitId(Long circuitId);

    List<AvisSpecialiste> findTop3AvisSpecialisteByCircuitId(Long circuitId);
}
