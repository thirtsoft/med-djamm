package com.meddjamm.sn.rh.remote.controller.api;

import com.meddjamm.sn.rh.remote.model.CodagePatientDetailDs;
import com.meddjamm.sn.rh.remote.model.CodagePatientDs;
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

@RequestMapping(value = APP_ROOT + "/codagepatient")
public interface CodagePatientApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    Long creerCodagePatient(@RequestBody CodagePatientDs codagePatientDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Long updateCodagePatient(@PathVariable Long id, @RequestBody CodagePatientDs codagePatientDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CodagePatientDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CodagePatientDetailDs>> findAllCodagePatients();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteCodagePatient(@PathVariable Long id);
}
