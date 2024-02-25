package com.meddjamm.sn.rh.services;

import com.meddjamm.sn.rh.entity.Specialite;

import java.util.List;

public interface SpecialiteService {

    void addSpeciality(Specialite specialite);

    void updateSpeciality(Long id, Specialite specialite);

    Specialite findById(Long id);

    List<Specialite> findAllSpecialities();

    void deleteSpeciality(Long id);
}
