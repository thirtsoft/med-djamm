package com.meddjamm.sn.dossiermedical.services;

import com.meddjamm.sn.dossiermedical.entity.Diagnostic;

import java.util.List;

public interface DiagnosticService {

    Diagnostic saveDiagnostic(Diagnostic diagnostic);

    Diagnostic updateDiagnostic(Long id, Diagnostic diagnostic);

    Diagnostic findById(Long id);

    List<Diagnostic> findAllDiagnostics();

    void deleteDiagnostic(Long id);

}
