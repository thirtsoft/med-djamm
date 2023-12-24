package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.Maladie;

import java.util.List;

public interface MaladieService {

    Maladie saveMaladie(Maladie maladie);

    Maladie updateMaladie(Long id, Maladie maladie) throws Exception;

    Maladie findById(Long id);

    List<Maladie> findAllMaladies();

    void deleteMaladie(Long id);
}
