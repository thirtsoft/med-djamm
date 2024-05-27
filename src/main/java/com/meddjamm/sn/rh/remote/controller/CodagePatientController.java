package com.meddjamm.sn.rh.remote.controller;

import com.meddjamm.sn.rh.assembler.CodagePatientAssembler;
import com.meddjamm.sn.rh.entity.CodagePatient;
import com.meddjamm.sn.rh.remote.controller.api.CodagePatientApi;
import com.meddjamm.sn.rh.remote.model.CodagePatientDetailDs;
import com.meddjamm.sn.rh.remote.model.CodagePatientDs;
import com.meddjamm.sn.rh.services.CodagePatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CodagePatientController implements CodagePatientApi {

    private final CodagePatientService codagePatientService;

    private final CodagePatientAssembler codagePatientAssembler;


    @Override
    public Long creerCodagePatient(CodagePatientDs codagePatientDs) {
        CodagePatient codagePatient = codagePatientAssembler.assembleCodagePatientFromDs(codagePatientDs);
        codagePatientService.saveCodagePatient(codagePatient);
        return codagePatient.getId();
    }

    @Override
    public Long updateCodagePatient(Long id, CodagePatientDs codagePatientDs) throws Exception {
        CodagePatient codagePatient = codagePatientAssembler.assembleCodagePatientFromDs(codagePatientDs);
        codagePatientService.updateCodagePatient(id, codagePatient);
        return codagePatient.getId();
    }

    @Override
    public ResponseEntity<CodagePatientDs> findById(Long id) {
        return new ResponseEntity<>(codagePatientAssembler.assembleEntityToDs(
                codagePatientService.findById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CodagePatientDetailDs>> findAllCodagePatients() {
        return new ResponseEntity<>(codagePatientAssembler.assembleEntitiesFromCodagePatientDetail(
                codagePatientService.findAllCodagePatients()
        ), HttpStatus.OK);
    }

    @Override
    public void deleteCodagePatient(Long id) {
        codagePatientService.deleteCodagePatient(id);
    }
}
