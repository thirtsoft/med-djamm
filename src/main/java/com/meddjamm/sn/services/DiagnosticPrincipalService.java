package com.meddjamm.sn.services;

import com.meddjamm.sn.model.DiagnosticPrincipal;

import java.util.List;

public interface DiagnosticPrincipalService {

    void saveDiagnosticPrincipal(DiagnosticPrincipal diagnosticPrincipal);

    void updateDiagnosticPrincipal(Long id, DiagnosticPrincipal diagnosticPrincipal) throws Exception;

    DiagnosticPrincipal findById(Long id);

    List<DiagnosticPrincipal> findAllDiagnosticPrincipals();

    void deleteDiagnosticPrincipal(Long id);
}
