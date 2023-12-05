package com.meddjamm.sn.controller;

import com.meddjamm.sn.controller.api.DiagnosticPrincipalApi;
import com.meddjamm.sn.model.DiagnosticPrincipal;
import com.meddjamm.sn.services.DiagnosticPrincipalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class DiagnosticPrincipalController implements DiagnosticPrincipalApi {

    private final DiagnosticPrincipalService diagnosticPrincipalService;

    public DiagnosticPrincipalController(DiagnosticPrincipalService diagnosticPrincipalService) {
        this.diagnosticPrincipalService = diagnosticPrincipalService;
    }

    @Override
    public void creerDiagnosticPrincipal(DiagnosticPrincipal diagnosticPrincipal) {
        diagnosticPrincipalService.saveDiagnosticPrincipal(diagnosticPrincipal);
    }

    @Override
    public void updateDiagnosticPrincipal(Long id, DiagnosticPrincipal diagnosticPrincipal) throws Exception {
        diagnosticPrincipalService.updateDiagnosticPrincipal(id, diagnosticPrincipal);
    }

    @Override
    public ResponseEntity<DiagnosticPrincipal> findById(Long id) {
        return new ResponseEntity<>(diagnosticPrincipalService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DiagnosticPrincipal>> findAllDiagnosticPrincipals() {
        return new ResponseEntity<>(diagnosticPrincipalService.findAllDiagnosticPrincipals(), HttpStatus.OK);
    }

    @Override
    public void deleteDiagnosticPrincipal(Long id) {
        diagnosticPrincipalService.deleteDiagnosticPrincipal(id);
    }
}
