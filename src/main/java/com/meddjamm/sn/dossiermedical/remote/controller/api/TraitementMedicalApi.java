package com.meddjamm.sn.dossiermedical.remote.controller.api;

import com.meddjamm.sn.dossiermedical.remote.model.TraitementMedicalDs;
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

@RequestMapping(value = APP_ROOT + "/traitementmedical")
public interface TraitementMedicalApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TraitementMedicalDs> creerTraitementMedical(@RequestBody TraitementMedicalDs traitementMedicalDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TraitementMedicalDs> updateTraitementMedical(@PathVariable Long id, @RequestBody TraitementMedicalDs traitementMedicalDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TraitementMedicalDs> findTraitementMedicalById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TraitementMedicalDs>> findAllTraitementMedicals();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteTraitementMedical(@PathVariable Long id);

    @GetMapping(value = "/by-patient/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TraitementMedicalDs>> findAllTraitementMedicalsByPatientId(@PathVariable String code);
    
}
