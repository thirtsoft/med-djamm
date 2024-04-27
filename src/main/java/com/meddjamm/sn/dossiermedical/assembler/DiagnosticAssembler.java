package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.dossiermedical.entity.Diagnostic;
import com.meddjamm.sn.dossiermedical.remote.model.DiagnosticDs;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiagnosticAssembler {

    private final PatientService patientService;

    public DiagnosticAssembler(PatientService patientService) {
        this.patientService = patientService;
    }

    public List<DiagnosticDs> assembleEntitiesFrom(List<Diagnostic> diagnostics) {
        return diagnostics.stream().map(this::assembleEntityToDs).toList();
    }

    public DiagnosticDs assembleEntityToDs(Diagnostic diagnostic) {
        if (diagnostic == null)
            return null;
        DiagnosticDs diagnosticDs = new DiagnosticDs();
        if (diagnostic.getId() != null)
            diagnosticDs.setId(diagnostic.getId());
        diagnosticDs.setActif(diagnostic.isActif());
        diagnosticDs.setDiagnostic_principal(diagnostic.getDiagnostic_principal());
        diagnosticDs.setDiagnostic_associe(diagnostic.getDiagnostic_associe());
        return diagnosticDs;
    }

    public Diagnostic assembleDiagnosticFromDs(DiagnosticDs diagnosticDs) {
        if (diagnosticDs == null)
            return null;
        Diagnostic diagnostic = new Diagnostic();
        if (diagnosticDs.getId() != null)
            diagnostic.setId(diagnosticDs.getId());
        diagnostic.setActif(diagnosticDs.isActif());
        diagnostic.setDiagnostic_principal(diagnosticDs.getDiagnostic_principal());
        diagnostic.setDiagnostic_associe(diagnosticDs.getDiagnostic_associe());
        return diagnostic;
    }

    public Diagnostic assembleUpdateDiagnosticFromDs(Diagnostic diagnostic, DiagnosticDs diagnosticDs) {
        diagnostic.setDiagnostic_principal(diagnosticDs.getDiagnostic_principal());
        diagnostic.setDiagnostic_associe(diagnosticDs.getDiagnostic_associe());
        return diagnostic;
    }
}
