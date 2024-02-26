package com.meddjamm.sn.dossiermedical.remote.controller;

import com.meddjamm.sn.dossiermedical.assembler.CircuitPatientAssembler;
import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import com.meddjamm.sn.dossiermedical.remote.controller.api.CircuitPatientApi;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientDs;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientListDs;
import com.meddjamm.sn.dossiermedical.services.CircuitPatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200/")
public class CircuitPatientController implements CircuitPatientApi {

    private final CircuitPatientAssembler circuitPatientAssembler;
    private final CircuitPatientService circuitPatientService;

    public CircuitPatientController(CircuitPatientAssembler circuitPatientAssembler,
                                    CircuitPatientService circuitPatientService) {
        this.circuitPatientAssembler = circuitPatientAssembler;
        this.circuitPatientService = circuitPatientService;
    }

    @Override
    public ResponseEntity<CircuitPatientDs> creerCircuitPatient(CircuitPatientDs circuitPatientDs) {
        CircuitPatient circuitPatient = circuitPatientAssembler.assembleCircuitPatientFromDs(circuitPatientDs);
        circuitPatientService.saveCircuitPatient(circuitPatient);
        return new ResponseEntity<>(circuitPatientAssembler.assembleEntityToDs(circuitPatient), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CircuitPatientDs> updateCircuitPatient(Long id, CircuitPatientDs circuitPatientDs) throws Exception {
        CircuitPatient circuitPatient = circuitPatientAssembler.assembleCircuitPatientFromDs(circuitPatientDs);
        circuitPatientService.updateCircuitPatient(id, circuitPatient);
        return new ResponseEntity<>(circuitPatientAssembler.assembleEntityToDs(circuitPatient), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CircuitPatientDetailDs> findCircuitPatientById(Long id) {
        return new ResponseEntity<>(circuitPatientAssembler.assembleEntityToDetailDs(
                circuitPatientService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CircuitPatientDetailDs> findCircuitPatientsByPatient(String code) {
        return new ResponseEntity<>(circuitPatientAssembler.assembleEntityToDetailDs(
                circuitPatientService.findCircuitPatientsByPatient(code)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CircuitPatientListDs>> findAllCircuitPatients() {
        return new ResponseEntity<>(circuitPatientAssembler.assembleEntitiesFrom(
                circuitPatientService.findAllCircuitPatients()
        ), HttpStatus.OK);
    }

    @Override
    public void deleteCircuitPatient(Long id) {
        circuitPatientService.deleteCircuitPatient(id);
    }
}
