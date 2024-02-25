package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.DiagnosticPrincipalAssembler;
import com.meddjamm.sn.entity.DiagnosticAssocie;
import com.meddjamm.sn.entity.DiagnosticPrincipal;
import com.meddjamm.sn.remote.controller.api.DiagnosticPrincipalApi;
import com.meddjamm.sn.remote.model.DiagnosticPrincipalDetailDs;
import com.meddjamm.sn.remote.model.DiagnosticPrincipalDs;
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

    private final DiagnosticPrincipalAssembler diagnosticPrincipalAssembler;

    public DiagnosticPrincipalController(DiagnosticPrincipalService diagnosticPrincipalService, DiagnosticPrincipalAssembler diagnosticPrincipalAssembler) {
        this.diagnosticPrincipalService = diagnosticPrincipalService;
        this.diagnosticPrincipalAssembler = diagnosticPrincipalAssembler;
    }

    @Override
    public void creerDiagnosticPrincipal(DiagnosticPrincipalDs diagnosticPrincipalDs) {
        DiagnosticPrincipal diagnosticAjouter = diagnosticPrincipalAssembler.assembleDiagnosticPrincipalFromDs(diagnosticPrincipalDs);
        diagnosticPrincipalService.saveDiagnosticPrincipal(diagnosticAjouter);
    }

    @Override
    public void updateDiagnosticPrincipal(Long id, DiagnosticPrincipalDs diagnosticPrincipalDs) throws Exception {
        DiagnosticPrincipal diagnosticAjouter = diagnosticPrincipalAssembler.assembleDiagnosticPrincipalFromDs(diagnosticPrincipalDs);
        diagnosticPrincipalService.updateDiagnosticPrincipal(id, diagnosticAjouter);
    }

    @Override
    public ResponseEntity<DiagnosticPrincipalDetailDs> findById(Long id) {
        return new ResponseEntity<>(diagnosticPrincipalAssembler.assemblesEntityToDs(diagnosticPrincipalService.findById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DiagnosticPrincipalDetailDs>> findAllDiagnosticPrincipals() {
        return new ResponseEntity<>(diagnosticPrincipalAssembler
                .assembleEntitiesFrom(diagnosticPrincipalService.findAllDiagnosticPrincipals()), HttpStatus.OK);
    }

    @Override
    public void deleteDiagnosticPrincipal(Long id) {
        diagnosticPrincipalService.deleteDiagnosticPrincipal(id);
    }
}
