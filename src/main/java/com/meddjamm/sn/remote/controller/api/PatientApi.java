package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.entity.Patient;
import com.meddjamm.sn.remote.model.PatientDetailDs;
import com.meddjamm.sn.remote.model.PatientMinDs;
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
    ResponseEntity<PatientDetailDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list")
    ResponseEntity<List<PatientMinDs>> findAllPatients();

    @DeleteMapping(value = "/delete/{id}")
    void deletePatient(@PathVariable Long id);

}