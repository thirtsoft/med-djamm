package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.DiagnosticAssocieAssembler;
import com.meddjamm.sn.entity.Classification;
import com.meddjamm.sn.entity.DiagnosticAssocie;
import com.meddjamm.sn.remote.controller.api.DiagnosticAssocieApi;
import com.meddjamm.sn.remote.model.ClassificationDs;
import com.meddjamm.sn.remote.model.DiagnosticAssocieDetailDs;
import com.meddjamm.sn.remote.model.DiagnosticAssocieDs;
import com.meddjamm.sn.services.DiagnosticAssocieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class DiagnosticAssocieController implements DiagnosticAssocieApi {

    private final DiagnosticAssocieService diagnosticAssocieService;

    private final DiagnosticAssocieAssembler diagnosticAssocieAssembler;

    public DiagnosticAssocieController(DiagnosticAssocieService diagnosticAssocieService, DiagnosticAssocieAssembler diagnosticAssocieAssembler) {
        this.diagnosticAssocieService = diagnosticAssocieService;
        this.diagnosticAssocieAssembler = diagnosticAssocieAssembler;
    }

    @Override
    public void creerDiagnosticAssocie(DiagnosticAssocieDs diagnosticAssocieDs) {
        DiagnosticAssocie diagnosticAssocieAjouter = diagnosticAssocieAssembler.assembleDiagnosticAssocieFromDs(diagnosticAssocieDs);
        diagnosticAssocieService.saveDiagnosticAssocie(diagnosticAssocieAjouter);
    }

    @Override
    public void updateDiagnosticAssocie(Long id, DiagnosticAssocieDs diagnosticAssocieDs) throws Exception {
        DiagnosticAssocie diagnosticAssocieModifier = diagnosticAssocieAssembler.assembleDiagnosticAssocieFromDs(diagnosticAssocieDs);
        diagnosticAssocieService.updateDiagnosticAssocie(id, diagnosticAssocieModifier);
    }

    @Override
    public ResponseEntity<DiagnosticAssocieDetailDs> findById(Long id) {
        DiagnosticAssocieDetailDs diagnosticAssocie = diagnosticAssocieAssembler.assemblesEntityToDs(diagnosticAssocieService.findById(id));
        return new ResponseEntity<>(diagnosticAssocie, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DiagnosticAssocieDetailDs>> findAllDiagnosticAssocies() {
        List<DiagnosticAssocieDetailDs> diagnosticAssocieDetailDs = diagnosticAssocieAssembler
                .assembleEntitiesFrom(diagnosticAssocieService.findAllDiagnosticAssocies());
        return new ResponseEntity<>(diagnosticAssocieDetailDs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DiagnosticAssocieDetailDs>> findDiagnosticAssociesByPatient(String indexPatient) {
        List<DiagnosticAssocieDetailDs> diagnosticAssocieDetailDs = diagnosticAssocieAssembler
                .assembleEntitiesFrom(diagnosticAssocieService.findAllDiagnosticAssocieByPatient(indexPatient));
        return new ResponseEntity<>(diagnosticAssocieDetailDs, HttpStatus.OK);
    }

    @Override
    public void deleteDiagnosticPrincipal(Long id) {
        diagnosticAssocieService.deleteDiagnosticAssocie(id);
    }
}
