package com.meddjamm.sn.controller;

import com.meddjamm.sn.controller.api.PatientApi;
import com.meddjamm.sn.model.Patient;
import com.meddjamm.sn.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class PatientController implements PatientApi {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
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
    public ResponseEntity<Patient> findById(Long id) {
        Patient patientResult = patientService.findById(id);
        return new ResponseEntity<>(patientResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Patient>> findAllPatients() {
        List<Patient> patientResult = patientService.findAllPatients();
        return new ResponseEntity<>(patientResult, HttpStatus.OK);
    }

    @Override
    public void deletePatient(Long id) {
        patientService.deletePatient(id);
    }
}
