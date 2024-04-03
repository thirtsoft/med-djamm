package com.meddjamm.sn.dossiermedical.remote.controller.api;

import com.meddjamm.sn.dossiermedical.remote.model.AllCircuitPatientDs;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientDs;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientListDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/circuitpatient")
public interface CircuitPatientApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CircuitPatientDs> creerCircuitPatient(@RequestBody CircuitPatientDs circuitPatientDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CircuitPatientDs> updateCircuitPatient(@PathVariable Long id, @RequestBody CircuitPatientDs circuitPatientDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CircuitPatientDetailDs> findCircuitPatientById(@PathVariable Long id);

    @GetMapping(value = "/patient/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CircuitPatientDetailDs> findCircuitPatientsByPatient(@PathVariable("code") String code);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CircuitPatientListDs>> findAllCircuitPatients();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteCircuitPatient(@PathVariable Long id);

    @GetMapping(value = "/by-patient/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AllCircuitPatientDs>> findAllCircuitPatientByPatientId(@PathVariable String code);

}
