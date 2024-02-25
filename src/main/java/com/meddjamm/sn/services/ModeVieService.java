package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.ModeVie;

import java.util.List;

public interface ModeVieService {

    void saveModeVie(ModeVie modeVie);

    void updateModeVie(Long id, ModeVie modeVie) throws Exception;

    ModeVie findById(Long id);

    List<ModeVie> findAllModeVieByPatient(String indexPatient);

    void deleteModeVie(Long id);
}
