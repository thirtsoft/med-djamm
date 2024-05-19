package com.meddjamm.sn.dossiermedical.remote.controller.api;

import com.meddjamm.sn.dossiermedical.remote.model.ObservationCliniqueDs;
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

@RequestMapping(value = APP_ROOT + "/observationclinique")
public interface ObservationCliniqueApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    void creerObservationClinique(@RequestBody ObservationCliniqueDs observationCliniqueDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateObservationClinique(@PathVariable Long id, @RequestBody ObservationCliniqueDs observationCliniqueDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ObservationCliniqueDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ObservationCliniqueDs>> findObservationCliniques();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteObservationClinique(@PathVariable Long id);
}
