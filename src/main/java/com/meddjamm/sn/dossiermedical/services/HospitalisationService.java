package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.Hospitalisation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HospitalisationService {

    Hospitalisation saveHospitalisation(Hospitalisation hospitalisation);

    Hospitalisation updateHospitalisation(Long id, Hospitalisation hospitalisation);

    Hospitalisation findById(Long id);

    List<Hospitalisation> findAllHospitalisations();

    List<Hospitalisation> findAllByPatient(String code);

    void deleteHospitalisation(Long id);

    boolean addExamBiologicToHospitalisation(Long biologicExamId, MultipartFile biologic) throws Exception;

    boolean addExamImmunologicToHospitalisation(Long immunologicExamId, MultipartFile immunologic) throws Exception;

    boolean addExamImagerToHospitalisation(Long imagerExamId, MultipartFile imager) throws Exception;

    boolean addExamHematologicToHospitalisation(Long hematologicExamId, MultipartFile hematologic) throws Exception;

}
