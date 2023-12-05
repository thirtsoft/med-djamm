package com.meddjamm.sn.controller.api;

import com.meddjamm.sn.model.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/patient")
public interface PatientApi {

    @PostMapping(value = "/save")
    ResponseEntity<Patient> creerPatient(@RequestBody Patient patient);

    @PutMapping(value = "/edit/{id}")
    ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) throws Exception;

    @GetMapping(value = "/{id}")
    ResponseEntity<Patient> findById(@PathVariable Long id);

    @GetMapping(value = "/list")
    ResponseEntity<List<Patient>> findAllPatients();

    @DeleteMapping(value = "/delete/{id}")
    void deletePatient(@PathVariable Long id);

}