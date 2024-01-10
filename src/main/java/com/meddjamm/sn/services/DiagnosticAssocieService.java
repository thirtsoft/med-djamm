package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.DiagnosticAssocie;

import java.util.List;

public interface DiagnosticAssocieService {

    void saveDiagnosticAssocie(DiagnosticAssocie diagnosticAssocie);

    void updateDiagnosticAssocie(Long id, DiagnosticAssocie diagnosticAssocie) throws Exception;

    DiagnosticAssocie findById(Long id);

    List<DiagnosticAssocie> findAllDiagnosticAssocies();

    List<DiagnosticAssocie> findAllDiagnosticAssocieByPatient(String indexPatient);

    void deleteDiagnosticAssocie(Long id);
}
