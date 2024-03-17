package com.meddjamm.sn.assembler;

import com.meddjamm.sn.dossiermedical.assembler.PatientAssembler;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import com.meddjamm.sn.entity.DiagnosticPrincipal;
import com.meddjamm.sn.remote.model.DiagnosticPrincipalDetailDs;
import com.meddjamm.sn.remote.model.DiagnosticPrincipalDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiagnosticPrincipalAssembler {

    private final PatientService patientService;

    private final PatientAssembler patientAssembler;

    private final MaladieAssembler maladieAssembler;

    public DiagnosticPrincipalAssembler(PatientService patientService,
                                        PatientAssembler patientAssembler,
                                        MaladieAssembler maladieAssembler
    ) {
        this.patientService = patientService;
        this.patientAssembler = patientAssembler;
        this.maladieAssembler = maladieAssembler;
    }

    public List<DiagnosticPrincipalDetailDs> assembleEntitiesFrom(List<DiagnosticPrincipal> diagnosticPrincipals) {
        return diagnosticPrincipals.stream().map(this::assemblesEntityToDs).toList();
    }

    public DiagnosticPrincipalDs assembleEntityToDs(DiagnosticPrincipal diagnosticPrincipal) {
        DiagnosticPrincipalDs diagnosticPrincipalDs = new DiagnosticPrincipalDs();
        diagnosticPrincipalDs.setId(diagnosticPrincipal.getId());
        diagnosticPrincipalDs.setScoreObtenu(diagnosticPrincipal.getScoreObtenu());
        diagnosticPrincipalDs.setCreateDate(diagnosticPrincipal.getCreateDate());
        diagnosticPrincipalDs.setActif(diagnosticPrincipal.isActif());
        diagnosticPrincipalDs.setMaladieDs(maladieAssembler.assembleEntityToDs(diagnosticPrincipal.getMaladie()));
        return diagnosticPrincipalDs;
    }

    public DiagnosticPrincipal assembleDiagnosticPrincipalFromDs(DiagnosticPrincipalDs diagnosticPrincipalDs) {
        DiagnosticPrincipal diagnosticPrincipal = new DiagnosticPrincipal();
        diagnosticPrincipal.setId(diagnosticPrincipalDs.getId());
        diagnosticPrincipal.setScoreObtenu(diagnosticPrincipalDs.getScoreObtenu());
        diagnosticPrincipal.setCreateDate(diagnosticPrincipalDs.getCreateDate());
        diagnosticPrincipal.setActif(diagnosticPrincipalDs.isActif());
        diagnosticPrincipal.setMaladie(maladieAssembler.assembleMaladieFromDs(diagnosticPrincipalDs.getMaladieDs()));
        return diagnosticPrincipal;
    }

    public DiagnosticPrincipalDetailDs assemblesEntityToDs(DiagnosticPrincipal diagnosticPrincipal) {
        DiagnosticPrincipalDetailDs diagnosticAssocieDetailDs = new DiagnosticPrincipalDetailDs();
        diagnosticAssocieDetailDs.setId(diagnosticPrincipal.getId());
        diagnosticAssocieDetailDs.setScoreObtenu(diagnosticPrincipal.getScoreObtenu());
        diagnosticAssocieDetailDs.setCreateDate(diagnosticPrincipal.getCreateDate());
        diagnosticAssocieDetailDs.setActif(diagnosticPrincipal.isActif());
        diagnosticAssocieDetailDs.setMaladieDs(maladieAssembler.assembleEntityToDs(diagnosticPrincipal.getMaladie()));
        return diagnosticAssocieDetailDs;
    }

}
