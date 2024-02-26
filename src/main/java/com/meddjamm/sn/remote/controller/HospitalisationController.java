package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.HospitalisationAssembler;
import com.meddjamm.sn.entity.Hospitalisation;
import com.meddjamm.sn.remote.controller.api.HospitalisationApi;
import com.meddjamm.sn.remote.model.HospitalisationDetailDs;
import com.meddjamm.sn.remote.model.HospitalisationDs;
import com.meddjamm.sn.services.HospitalisationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
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
        Hospitalisation hospitalisation = hospitalisationAssembler.assembleHospitalisationFromDs(hospitalisationDs);
        return new ResponseEntity<>(hospitalisationAssembler.assembleEntitiesToDs(
        hospitalisationService.saveHospitalisation(hospitalisation)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HospitalisationDetailDs> updateHospitalisation(Long id, HospitalisationDs hospitalisationDs) throws Exception {
        Hospitalisation hospitalisation = hospitalisationAssembler.assembleHospitalisationFromDs(hospitalisationDs);
        return new ResponseEntity<>(hospitalisationAssembler.assembleEntitiesToDs(
                hospitalisationService.updateHospitalisation(id, hospitalisation)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HospitalisationDetailDs> findById(Long id) {
        return new ResponseEntity<>(hospitalisationAssembler.assembleEntitiesToDs(
                hospitalisationService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HospitalisationDetailDs>> findAllHospitalisations() {
        return new ResponseEntity<>(hospitalisationAssembler
                .assembleEntitiesFrom(hospitalisationService.findAllHospitalisations()), HttpStatus.OK);
    }

    @Override
    public void deleteHospitalisation(Long id) {
        hospitalisationService.deleteHospitalisation(id);
    }

}
