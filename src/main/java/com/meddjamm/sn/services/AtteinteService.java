package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.Atteinte;

import java.util.List;

public interface AtteinteService {

    void saveAtteinte(Atteinte atteinte);

    void updateAtteinte(Long id, Atteinte atteinte) throws Exception;

    Atteinte findById(Long id);

    List<Atteinte> findAllAtteintes();

    void deleteAtteinte(Long id);
}
