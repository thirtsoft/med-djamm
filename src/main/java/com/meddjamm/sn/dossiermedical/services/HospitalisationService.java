package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.Hospitalisation;

import java.util.List;

public interface HospitalisationService {

    Hospitalisation saveHospitalisation(Hospitalisation hospitalisation);

    Hospitalisation updateHospitalisation(Long id, Hospitalisation hospitalisation);

    Hospitalisation findById(Long id);

    List<Hospitalisation> findAllHospitalisations();

    List<Hospitalisation> findAllByPatient(String code);

    void deleteHospitalisation(Long id);
}
