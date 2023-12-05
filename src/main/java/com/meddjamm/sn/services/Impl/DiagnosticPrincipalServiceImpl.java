package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.model.DiagnosticPrincipal;
import com.meddjamm.sn.repository.DiagnosticPrincipalRepository;
import com.meddjamm.sn.services.DiagnosticPrincipalService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiagnosticPrincipalServiceImpl implements DiagnosticPrincipalService {

    private final DiagnosticPrincipalRepository diagnosticPrincipalRepository;

    public DiagnosticPrincipalServiceImpl(DiagnosticPrincipalRepository diagnosticPrincipalRepository) {
        this.diagnosticPrincipalRepository = diagnosticPrincipalRepository;
    }

    @Override
    public void saveDiagnosticPrincipal(DiagnosticPrincipal diagnosticPrincipal) {
        diagnosticPrincipal.setActif(true);
        diagnosticPrincipal.setCreateDate(new Date());
        diagnosticPrincipalRepository.save(diagnosticPrincipal);
    }

    @Override
    public void updateDiagnosticPrincipal(Long id, DiagnosticPrincipal diagnosticPrincipal) throws Exception {
        diagnosticPrincipal.setId(id);
        diagnosticPrincipalRepository.save(diagnosticPrincipal);
    }

    @Override
    public DiagnosticPrincipal findById(Long id) {
        return diagnosticPrincipalRepository.findDiagnosticPrincipalById(id);
    }

    @Override
    public List<DiagnosticPrincipal> findAllDiagnosticPrincipals() {
        return diagnosticPrincipalRepository.findAll();
    }

    @Override
    public void deleteDiagnosticPrincipal(Long id) {
        DiagnosticPrincipal diagnosticPrincipal = diagnosticPrincipalRepository.findDiagnosticPrincipalById(id);
        diagnosticPrincipal.setActif(false);
        diagnosticPrincipalRepository.save(diagnosticPrincipal);
    }
}
