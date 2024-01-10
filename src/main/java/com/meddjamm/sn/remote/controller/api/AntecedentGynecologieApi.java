package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.remote.model.AntecedentGynecologieDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/antecedent-gynecologie")
public interface AntecedentGynecologieApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    void creerAntecedentGynecologie(@RequestBody AntecedentGynecologieDs antecedentGynecologieDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateAntecedentGynecologie(@PathVariable Long id, @RequestBody AntecedentGynecologieDs antecedentGynecologieDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AntecedentGynecologieDs> findById(@PathVariable Long id);

    @GetMapping(value = "/patient/{indexPatient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AntecedentGynecologieDs>> findAntecedentGynecologiesByPatient(@PathVariable("indexPatient") String indexPatient);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteAntecedentGynecologie(@PathVariable Long id);

}
