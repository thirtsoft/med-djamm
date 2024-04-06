package com.meddjamm.sn.dossiermedical.remote.controller;

import com.meddjamm.sn.dossiermedical.assembler.DiagnosticAssembler;
import com.meddjamm.sn.dossiermedical.entity.Diagnostic;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.remote.controller.api.DiagnosticApi;
import com.meddjamm.sn.dossiermedical.remote.model.DiagnosticDs;
import com.meddjamm.sn.dossiermedical.repository.PatientRepository;
import com.meddjamm.sn.dossiermedical.services.DiagnosticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiagnosticController implements DiagnosticApi {

    private final DiagnosticService diagnosticService;

    private final DiagnosticAssembler diagnosticAssembler;

    private final PatientRepository patientRepository;

    public DiagnosticController(DiagnosticService diagnosticService,
                                DiagnosticAssembler diagnosticAssembler,
                                PatientRepository patientRepository) {
        this.diagnosticService = diagnosticService;
        this.diagnosticAssembler = diagnosticAssembler;
        this.patientRepository = patientRepository;
    }

    @Override
    public ResponseEntity<DiagnosticDs> creerDiagnostic(DiagnosticDs diagnosticDs) {
        Diagnostic diagnostic = diagnosticService.saveDiagnostic(diagnosticAssembler
                .assembleDiagnosticFromDs(diagnosticDs));
        Patient patient = patientRepository.findPatientByCode(diagnosticDs.getCodePatient());
        patient.setDiagnostic(diagnostic);
        patientRepository.save(patient);
        return new ResponseEntity<>(diagnosticAssembler.assembleEntityToDs(diagnostic), HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<DiagnosticDs> updateDiagnostic(Long id, DiagnosticDs diagnosticDs) {
        return null;
    }

    @Override
    public ResponseEntity<DiagnosticDs> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<DiagnosticDs>> findAllDiagnostics() {
        return null;
    }

    @Override
    public void deleteDiagnostic(Long id) {

    }
}
