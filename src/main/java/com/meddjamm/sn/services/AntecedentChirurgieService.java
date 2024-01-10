package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.AntecedentChirurgie;

import java.util.List;

public interface AntecedentChirurgieService {

    void saveAntecedentChirurgie(AntecedentChirurgie antecedentChirurgie);

    void updateAntecedentChirurgie(Long id, AntecedentChirurgie antecedentChirurgie) throws Exception;

    AntecedentChirurgie findById(Long id);

    List<AntecedentChirurgie> findAllAntecedentChirurgieByPatient(String indexPatient);

    void deleteAntecedentChirurgie(Long id);
}
