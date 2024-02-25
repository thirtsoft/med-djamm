package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.DiagnosticPrincipal;

import java.util.List;

public interface DiagnosticPrincipalService {

    void saveDiagnosticPrincipal(DiagnosticPrincipal diagnosticPrincipal);

    void updateDiagnosticPrincipal(Long id, DiagnosticPrincipal diagnosticPrincipal) throws Exception;

    DiagnosticPrincipal findById(Long id);

    List<DiagnosticPrincipal> findAllDiagnosticPrincipals();

    List<DiagnosticPrincipal> findDiagnosticPrincipalsByPatient(String indexPatient);

    void deleteDiagnosticPrincipal(Long id);
}
