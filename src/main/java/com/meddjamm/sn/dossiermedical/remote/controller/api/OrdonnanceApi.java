package com.meddjamm.sn.dossiermedical.remote.controller.api;

import com.meddjamm.sn.dossiermedical.remote.model.OrdonnanceDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/ordonnance")
public interface OrdonnanceApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrdonnanceDs> creerOrdonnance(@RequestBody OrdonnanceDs ordonnanceDs);

    @PatchMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrdonnanceDs> updateOrdonnance(@PathVariable Long id, @RequestBody OrdonnanceDs ordonnanceDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrdonnanceDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrdonnanceDs>> findAllOrdonnances();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteOrdonnance(@PathVariable Long id);

    @GetMapping(value = "/by-patient/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrdonnanceDs>> findAllOrdonnancesByPatientId(@PathVariable String code);
}
