package com.meddjamm.sn.rh.services;

import com.meddjamm.sn.rh.entity.Medicament;

import java.util.List;

public interface MedicamentService {

    Medicament saveMedicament(Medicament medicament);

    Medicament updateMedicament(Long id, Medicament medicament) throws Exception;

    Medicament findById(Long id);

    Medicament findByCode(String code);

    List<Medicament> findAllMedicaments();

    void deleteMedicament(Long id);
}
