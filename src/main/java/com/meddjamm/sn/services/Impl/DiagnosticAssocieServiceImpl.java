package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.entity.DiagnosticAssocie;
import com.meddjamm.sn.repository.DiagnosticAssocieRepository;
import com.meddjamm.sn.services.DiagnosticAssocieService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class DiagnosticAssocieServiceImpl implements DiagnosticAssocieService {

    private final DiagnosticAssocieRepository diagnosticAssocieRepository;

    public DiagnosticAssocieServiceImpl(DiagnosticAssocieRepository diagnosticAssocieRepository) {
        this.diagnosticAssocieRepository = diagnosticAssocieRepository;
    }

    @Override
    public void saveDiagnosticAssocie(DiagnosticAssocie diagnosticAssocie) {
        diagnosticAssocie.setActif(true);
        diagnosticAssocieRepository.save(diagnosticAssocie);
    }

    @Override
    public void updateDiagnosticAssocie(Long id, DiagnosticAssocie diagnosticAssocie) throws Exception {
        if (!diagnosticAssocieRepository.existsById(id)) {
            throw new Exception("This DiagnosticAssocie is not found");
        }
        DiagnosticAssocie diagnosticAssocieResult = diagnosticAssocieRepository.findDiagnosticAssocieById(id);
        if (diagnosticAssocieResult == null) {
            throw new Exception("This DiagnosticAssocie is not found");
        }
        /*
        diagnosticAssocieResult.setLibelle(diagnosticAssocie.getLibelle());
        diagnosticAssocieResult.setIndexPatient(diagnosticAssocie.getIndexPatient());*/
        diagnosticAssocieResult.setLibellesDiagnostic(new HashSet<>(diagnosticAssocie.getLibellesDiagnostic()));
        diagnosticAssocieRepository.save(diagnosticAssocieResult);
    }

    @Override
    public DiagnosticAssocie findById(Long id) {
        return diagnosticAssocieRepository.findDiagnosticAssocieById(id);
    }

    @Override
    public List<DiagnosticAssocie> findAllDiagnosticAssocies() {
        return diagnosticAssocieRepository.findAllDiagnosticAssocies();
    }

    @Override
    public List<DiagnosticAssocie> findAllDiagnosticAssocieByPatient(String indexPatient) {
        return null;
    }

    @Override
    public void deleteDiagnosticAssocie(Long id) {
        DiagnosticAssocie diagnosticAssocie = findById(id);
        diagnosticAssocie.setActif(false);
        diagnosticAssocieRepository.save(diagnosticAssocie);
    }
}
