package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.remote.model.HistoireMaladieDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/histoire-maladie")
public interface HistoireMaladieApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    void creerHistoireMaladie(@RequestBody HistoireMaladieDs histoireMaladieDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateHistoireMaladie(@PathVariable Long id, @RequestBody HistoireMaladieDs histoireMaladieDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HistoireMaladieDs> findById(@PathVariable Long id);

    @GetMapping(value = "/patient/{indexPatient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<HistoireMaladieDs>> findHistoireMaladiesByPatient(@PathVariable("indexPatient") String indexPatient);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteHistoireMaladie(@PathVariable Long id);
}
