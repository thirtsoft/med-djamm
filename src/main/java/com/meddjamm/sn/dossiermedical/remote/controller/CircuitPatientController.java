package com.meddjamm.sn.dossiermedical.remote.controller;

import com.meddjamm.sn.dossiermedical.assembler.AvisSpecialisteAssembler;
import com.meddjamm.sn.dossiermedical.assembler.CircuitPatientAssembler;
import com.meddjamm.sn.dossiermedical.assembler.ConsultationMedicalAssembler;
import com.meddjamm.sn.dossiermedical.assembler.HospitalisationAssembler;
import com.meddjamm.sn.dossiermedical.assembler.OrdonnanceAssembler;
import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import com.meddjamm.sn.dossiermedical.remote.controller.api.CircuitPatientApi;
import com.meddjamm.sn.dossiermedical.remote.model.AllCircuitPatientDs;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientDs;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientListDs;
import com.meddjamm.sn.dossiermedical.services.AvisSpecialisteService;
import com.meddjamm.sn.dossiermedical.services.CircuitPatientService;
import com.meddjamm.sn.dossiermedical.services.ConsultationMedicalService;
import com.meddjamm.sn.dossiermedical.services.HospitalisationService;
import com.meddjamm.sn.dossiermedical.services.OrdonnanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CircuitPatientController implements CircuitPatientApi {

    private final CircuitPatientAssembler circuitPatientAssembler;
    private final CircuitPatientService circuitPatientService;

    private final OrdonnanceService ordonnanceService;

    private final OrdonnanceAssembler ordonnanceAssembler;

    private final ConsultationMedicalService consultationMedicalService;

    private final ConsultationMedicalAssembler consultationMedicalAssembler;
    private final AvisSpecialisteService avisSpecialisteService;

    private final AvisSpecialisteAssembler avisSpecialisteAssembler;

    private final HospitalisationService hospitalisationService;

    private final HospitalisationAssembler hospitalisationAssembler;

    public CircuitPatientController(CircuitPatientAssembler circuitPatientAssembler,
                                    CircuitPatientService circuitPatientService,
                                    OrdonnanceService ordonnanceService,
                                    OrdonnanceAssembler ordonnanceAssembler,
                                    ConsultationMedicalService consultationMedicalService,
                                    ConsultationMedicalAssembler consultationMedicalAssembler,
                                    AvisSpecialisteService avisSpecialisteService,
                                    AvisSpecialisteAssembler avisSpecialisteAssembler,
                                    HospitalisationService hospitalisationService,
                                    HospitalisationAssembler hospitalisationAssembler) {
        this.circuitPatientAssembler = circuitPatientAssembler;
        this.circuitPatientService = circuitPatientService;
        this.ordonnanceService = ordonnanceService;
        this.ordonnanceAssembler = ordonnanceAssembler;
        this.consultationMedicalService = consultationMedicalService;
        this.consultationMedicalAssembler = consultationMedicalAssembler;
        this.avisSpecialisteService = avisSpecialisteService;
        this.avisSpecialisteAssembler = avisSpecialisteAssembler;
        this.hospitalisationService = hospitalisationService;
        this.hospitalisationAssembler = hospitalisationAssembler;
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
    public ResponseEntity<List<CircuitPatientListDs>> findAllCircuitPatientsByPatient(String code) {
        return new ResponseEntity<>(circuitPatientAssembler.assembleEntitiesFrom(
                circuitPatientService.findAllCircuitPatientsByPatient(code)
        ), HttpStatus.OK);
    }

    @Override
    public void deleteCircuitPatient(Long id) {
        circuitPatientService.deleteCircuitPatient(id);
    }

    @Override
    public ResponseEntity<List<AllCircuitPatientDs>> findAllCircuitPatientByPatientId(String code) {
        List<AllCircuitPatientDs> ordonnanceCircuitPatientDs =
                ordonnanceAssembler.assembleAllCircuitPatientEntitiesFrom(
                        ordonnanceService.findOrdonnancesByPatientId(code)
                );
        List<AllCircuitPatientDs> consultationCircuitPatientDs = consultationMedicalAssembler.
                assembleAllCircuitPatientEntitiesFrom(
                        consultationMedicalService.findConsultationMedicalByPatientId(code)
                );
        List<AllCircuitPatientDs> avisCircuitPatientDs = avisSpecialisteAssembler.
                assembleAllCircuitPatientEntitiesFrom(
                        avisSpecialisteService.findAvisSpecialisteByPatientId(code)
                );
        List<AllCircuitPatientDs> hospitalisationCircuitPatientDs = hospitalisationAssembler.
                assembleAllCircuitPatientEntitiesFrom(
                        hospitalisationService.findAllByPatient(code)
                );
        List<AllCircuitPatientDs> allCircuitPatientDs = new ArrayList<>();
        allCircuitPatientDs.addAll(ordonnanceCircuitPatientDs);
        allCircuitPatientDs.addAll(consultationCircuitPatientDs);
        allCircuitPatientDs.addAll(avisCircuitPatientDs);
        allCircuitPatientDs.addAll(hospitalisationCircuitPatientDs);
        return new ResponseEntity<>(allCircuitPatientDs, HttpStatus.OK);
    }
}
