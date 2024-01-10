package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.remote.model.AntecedentMedicauxDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/antecedent-medicaux")
public interface AntecedentMedicauxApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    void creerAntecedentMedicaux(@RequestBody AntecedentMedicauxDs antecedentMedicauxDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateAntecedentMedicauxDs(@PathVariable Long id, @RequestBody AntecedentMedicauxDs antecedentMedicauxDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AntecedentMedicauxDs> findById(@PathVariable Long id);

    @GetMapping(value = "/patient/{indexPatient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AntecedentMedicauxDs>> findAntecedentMedicauxDsByPatient(@PathVariable("indexPatient") String indexPatient);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteAntecedentMedicauxDs(@PathVariable Long id);
}
