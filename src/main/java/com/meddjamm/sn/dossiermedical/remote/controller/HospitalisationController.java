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
    public ResponseEntity<HospitalisationDetailDs> creerHospitalisation(HospitalisationDs hospitalisationDs) {
        return new ResponseEntity<>(hospitalisationAssembler.assembleHospitalisationDetailDsFromHospitalisation(
                hospitalisationService.saveHospitalisation(
                        hospitalisationAssembler.assembleDsToEntity(hospitalisationDs)
                )
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HospitalisationDetailDs> updateHospitalisation(Long id, HospitalisationDs hospitalisationDs) {
        return new ResponseEntity<>(hospitalisationAssembler.assembleHospitalisationDetailDsFromHospitalisation(
                hospitalisationService.updateHospitalisation(id,
                        hospitalisationAssembler.assembleDsToEntity(hospitalisationDs)
                )
        ), HttpStatus.OK);
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

}
