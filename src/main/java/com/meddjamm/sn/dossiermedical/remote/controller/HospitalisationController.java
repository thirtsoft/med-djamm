package com.meddjamm.sn.dossiermedical.remote.controller;

import com.meddjamm.sn.dossiermedical.assembler.HospitalisationAssembler;
import com.meddjamm.sn.dossiermedical.remote.controller.api.HospitalisationApi;
import com.meddjamm.sn.dossiermedical.remote.model.HospitalisationDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.HospitalisationDs;
import com.meddjamm.sn.dossiermedical.remote.model.HospitalisationListDs;
import com.meddjamm.sn.dossiermedical.services.HospitalisationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class HospitalisationController implements HospitalisationApi {

    private final HospitalisationService hospitalisationService;

    private final HospitalisationAssembler hospitalisationAssembler;

    public HospitalisationController(HospitalisationService hospitalisationService,
                                     HospitalisationAssembler hospitalisationAssembler) {
        this.hospitalisationService = hospitalisationService;
        this.hospitalisationAssembler = hospitalisationAssembler;
    }

    @Override
    public void creerHospitalisation(HospitalisationDs hospitalisationDs) {
        hospitalisationService.saveHospitalisation(hospitalisationAssembler.assembleDsToEntity(hospitalisationDs));

    }

    @Override
    public void updateHospitalisation(Long id, HospitalisationDs hospitalisationDs) {
        hospitalisationService.updateHospitalisation(id, hospitalisationAssembler.assembleUpdateHospitalisation(hospitalisationDs));
    }

    @Override
    public ResponseEntity<HospitalisationDetailDs> findById(Long id) {
        return new ResponseEntity<>(hospitalisationAssembler.assembleHospitalisationDetailDsFromHospitalisation(
                hospitalisationService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HospitalisationListDs>> findAllHospitalisations() {
        return new ResponseEntity<>(hospitalisationAssembler
                .assembleEntitiesFrom(hospitalisationService.findAllHospitalisations()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HospitalisationListDs>> getHospitalisationListByPatient(String code) {
        return new ResponseEntity<>(hospitalisationAssembler
                .assembleEntitiesFrom(hospitalisationService.findAllByPatient(code)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HospitalisationDetailDs>> getHospitalisationsDetailsByPatient(String code) {
        return new ResponseEntity<>(hospitalisationAssembler.assembleHospitalisationsDetailsFromEntity(
                hospitalisationService.findAllByPatient((code)
                )
        ), HttpStatus.OK);

    }

    @Override
    public void deleteHospitalisation(Long id) {
        hospitalisationService.deleteHospitalisation(id);
    }

    @Override
    public boolean addExamBiologicToHospitalisation(Long hospitalisationId, MultipartFile biologic) throws Exception {
        return hospitalisationService.addExamBiologicToHospitalisation(hospitalisationId, biologic);
    }

    @Override
    public boolean addExamImmunologicToHospitalisation(Long hospitalisationId, MultipartFile immunologic) throws Exception {
        return hospitalisationService.addExamImmunologicToHospitalisation(hospitalisationId, immunologic);
    }

    @Override
    public boolean addExamImagerToHospitalisation(Long hospitalisationId, MultipartFile imager) throws Exception {
        return hospitalisationService.addExamImagerToHospitalisation(hospitalisationId, imager);
    }

    @Override
    public boolean addExamHematologicToHospitalisation(Long hospitalisationId, MultipartFile hematologic) throws Exception {
        return hospitalisationService.addExamHematologicToHospitalisation(hospitalisationId, hematologic);
    }


}
