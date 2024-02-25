package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.remote.model.DiagnosticAssocieDetailDs;
import com.meddjamm.sn.remote.model.DiagnosticAssocieDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/diagnostic-associe")
public interface DiagnosticAssocieApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    void creerDiagnosticAssocie(@RequestBody DiagnosticAssocieDs diagnosticAssocieDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateDiagnosticAssocie(@PathVariable Long id, @RequestBody DiagnosticAssocieDs diagnosticAssocieDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DiagnosticAssocieDetailDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<DiagnosticAssocieDetailDs>> findAllDiagnosticAssocies();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteDiagnosticPrincipal(@PathVariable Long id);
}
