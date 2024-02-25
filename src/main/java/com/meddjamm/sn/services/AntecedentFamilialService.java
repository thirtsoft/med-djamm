package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.AntecedentFamilial;

import java.util.List;

public interface AntecedentFamilialService {

    void saveAntecedentFamilial(AntecedentFamilial antecedentFamilial);

    void updateAntecedentFamilial(Long id, AntecedentFamilial antecedentFamilial) throws Exception;

    AntecedentFamilial findById(Long id);

    List<AntecedentFamilial> findAllAntecedentFamilialByPatient(String indexPatient);

    void deleteAntecedentFamilial(Long id);
}
