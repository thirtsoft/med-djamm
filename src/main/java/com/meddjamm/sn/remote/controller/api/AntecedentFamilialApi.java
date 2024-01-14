package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.remote.model.AntecedentFamilialDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/antecedent-familial")
public interface AntecedentFamilialApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    void creerAntecedentFamilial(@RequestBody AntecedentFamilialDs antecedentFamilialDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateAntecedentFamilial(@PathVariable Long id, @RequestBody AntecedentFamilialDs antecedentFamilialDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AntecedentFamilialDs> findById(@PathVariable Long id);

    @GetMapping(value = "/patient/{indexPatient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AntecedentFamilialDs>> findAntecedentFamilialsByPatient(@PathVariable("indexPatient") String indexPatient);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteAntecedentFamilialDs(@PathVariable Long id);
}
