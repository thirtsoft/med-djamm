package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.Hospitalisation;

import java.util.List;

public interface HospitalisationService {

    Hospitalisation saveHospitalisation(Hospitalisation hospitalisation);

    Hospitalisation updateHospitalisation(Long id, Hospitalisation hospitalisation) throws Exception;

    Hospitalisation findById(Long id);

    List<Hospitalisation> findAllHospitalisations();

    void deleteHospitalisation(Long id);
}
