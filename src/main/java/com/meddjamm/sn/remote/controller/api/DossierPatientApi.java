package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.remote.model.DossierPatientDetailDs;
import com.meddjamm.sn.remote.model.DossierPatientDs;
import com.meddjamm.sn.remote.model.DossierPatientListDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/dossier-patient")
public interface DossierPatientApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    void creerDossierPatient(@RequestBody DossierPatientDs dossierPatientDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateDossierPatient(@PathVariable Long id, @RequestBody DossierPatientDs dossierPatientDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DossierPatientDetailDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<DossierPatientDetailDs>> findDossierPatients();

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<DossierPatientListDs>> findAllDossierPatients();

    @GetMapping(value = "/patient/{indexPatient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<DossierPatientDetailDs>> findDossierPatientsByPatient(@PathVariable("indexPatient") String indexPatient);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteDossierPatient(@PathVariable Long id);
}
