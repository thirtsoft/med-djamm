package com.meddjamm.sn.dossiermedical.services.Impl;

import com.meddjamm.sn.dossiermedical.entity.Diagnostic;
import com.meddjamm.sn.dossiermedical.repository.DiagnosticRepository;
import com.meddjamm.sn.dossiermedical.services.DiagnosticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DiagnosticServiceImpl implements DiagnosticService {

    private final DiagnosticRepository diagnosticRepository;

    public DiagnosticServiceImpl(DiagnosticRepository diagnosticRepository) {
        this.diagnosticRepository = diagnosticRepository;
    }

    @Override
    public Diagnostic saveDiagnostic(Diagnostic diagnostic) {
        return diagnosticRepository.save(diagnostic);
    }

    @Override
    public Diagnostic updateDiagnostic(Long id, Diagnostic diagnostic) {
        if (!diagnosticRepository.existsById(id)) {
            log.info("Diagnostic that id is " + id + "not found");
        }
        diagnostic.setId(id);
        return diagnosticRepository.save(diagnostic);
    }

    @Override
    public Diagnostic findById(Long id) {
        return diagnosticRepository.findDiagnosticById(id);
    }

    @Override
    public List<Diagnostic> findAllDiagnostics() {
        return diagnosticRepository.findAllDiagnostics();
    }

    @Override
    public void deleteDiagnostic(Long id) {
        Diagnostic diagnostic = findById(id);
        diagnostic.setActif(false);
        diagnosticRepository.save(diagnostic);
    }
}
