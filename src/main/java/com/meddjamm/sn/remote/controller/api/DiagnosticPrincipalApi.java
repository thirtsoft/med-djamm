package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.remote.model.DiagnosticPrincipalDetailDs;
import com.meddjamm.sn.remote.model.DiagnosticPrincipalDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/diagnostic-principal")
public interface DiagnosticPrincipalApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    void creerDiagnosticPrincipal(@RequestBody DiagnosticPrincipalDs diagnosticPrincipalDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateDiagnosticPrincipal(@PathVariable Long id, @RequestBody DiagnosticPrincipalDs diagnosticPrincipalDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DiagnosticPrincipalDetailDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<DiagnosticPrincipalDetailDs>> findAllDiagnosticPrincipals();

    @GetMapping(value = "/patient/{indexPatient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<DiagnosticPrincipalDetailDs>> findDiagnosticAssociesByPatient(@PathVariable("indexPatient") String indexPatient);


    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteDiagnosticPrincipal(@PathVariable Long id);
}
