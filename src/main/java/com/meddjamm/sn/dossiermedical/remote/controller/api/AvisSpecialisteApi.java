package com.meddjamm.sn.dossiermedical.remote.controller.api;

import com.meddjamm.sn.dossiermedical.remote.model.AvisSpecialisteDs;
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

@RequestMapping(value = APP_ROOT + "/avisspecialiste")
public interface AvisSpecialisteApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AvisSpecialisteDs> creerAvisSpecialiste(@RequestBody AvisSpecialisteDs avisSpecialisteDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AvisSpecialisteDs> updateAvisSpecialiste(@PathVariable Long id, @RequestBody AvisSpecialisteDs avisSpecialisteDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AvisSpecialisteDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AvisSpecialisteDs>> findAllAvisSpecialistes();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteAvisSpecialiste(@PathVariable Long id);

    @GetMapping(value = "/by-patient/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AvisSpecialisteDs>> findAllAvisSpecialistesByPatientId(@PathVariable String code);

    @GetMapping(value = "/by-circuit/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AvisSpecialisteDs>> findAllAvisSpecialistesByCircuitId(@PathVariable Long code);

}
