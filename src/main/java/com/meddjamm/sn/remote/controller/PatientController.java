package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.PatientAssembler;
import com.meddjamm.sn.entity.Patient;
import com.meddjamm.sn.remote.controller.api.PatientApi;
import com.meddjamm.sn.remote.model.PatientDetailDs;
import com.meddjamm.sn.remote.model.PatientMinDs;
import com.meddjamm.sn.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class PatientController implements PatientApi {

    private final PatientService patientService;
    private final PatientAssembler patientAssembler;

    public PatientController(PatientService patientService, PatientAssembler patientAssembler) {
        this.patientService = patientService;
        this.patientAssembler = patientAssembler;
    }

    @Override
    public ResponseEntity<Patient> creerPatient(Patient patient) {
        Patient patientResult = patientService.savePatient(patient);
        return new ResponseEntity<>(patientResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Patient> updatePatient(Long id, Patient patient) throws Exception {
        Patient patientResult = patientService.updatePatient(id, patient);
        return new ResponseEntity<>(patientResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PatientDetailDs> findById(Long id) {
        PatientDetailDs patientResult = patientAssembler.assemblePatientDetails(patientService.findById(id));
        return new ResponseEntity<>(patientResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PatientMinDs>> findAllPatients() {
        List<PatientMinDs> patientResult = patientAssembler.assembleEntitiesFrom(patientService.findAllPatients());
        return new ResponseEntity<>(patientResult, HttpStatus.OK);
    }

    @Override
    public void deletePatient(Long id) {
        patientService.deletePatient(id);
    }
}
