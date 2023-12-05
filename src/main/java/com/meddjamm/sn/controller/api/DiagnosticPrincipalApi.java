package com.meddjamm.sn.controller.api;

import com.meddjamm.sn.model.Classification;
import com.meddjamm.sn.model.DiagnosticPrincipal;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/diagnostic-principal")
public interface DiagnosticPrincipalApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    void creerDiagnosticPrincipal(@RequestBody DiagnosticPrincipal diagnosticPrincipal);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateDiagnosticPrincipal(@PathVariable Long id, @RequestBody DiagnosticPrincipal diagnosticPrincipal) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DiagnosticPrincipal> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<DiagnosticPrincipal>> findAllDiagnosticPrincipals();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteDiagnosticPrincipal(@PathVariable Long id);
}
