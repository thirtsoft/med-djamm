package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.Specialite;

import java.util.List;

public interface SpecialiteService {

    void addSpeciality(Specialite specialite);

    void updateSpeciality(Long id, Specialite specialite);

    Specialite findById(Long id);

    List<Specialite> findAllSpecialities();

    void deleteSpeciality(Long id);
}
