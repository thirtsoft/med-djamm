package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.Hospitalisation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HospitalisationService {

    Long saveHospitalisation(Hospitalisation hospitalisation);

    Long updateHospitalisation(Long id, Hospitalisation hospitalisation);

    Hospitalisation findById(Long id);

    List<Hospitalisation> findAllHospitalisations();

    List<Hospitalisation> findAllByPatient(String code);

    void deleteHospitalisation(Long id);

    boolean addExamBiologicToHospitalisation(Long hospitalisationId, MultipartFile biologic) throws Exception;

    boolean addExamImmunologicToHospitalisation(Long hospitalisationId, MultipartFile immunologic) throws Exception;

    boolean addExamImagerToHospitalisation(Long hospitalisationId, MultipartFile imager) throws Exception;

    boolean addExamHematologicToHospitalisation(Long hospitalisationId, MultipartFile hematologic) throws Exception;

    boolean addProtocolMedicalTraitFileToHospitalisation(Long hospitalisationId, MultipartFile protocol) throws Exception;

    int countHospitalisationHomme();

    int countHospitalisationFemme();

    long countHospitalisation();


}
