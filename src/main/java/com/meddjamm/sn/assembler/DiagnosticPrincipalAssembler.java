package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.DiagnosticPrincipal;
import com.meddjamm.sn.entity.Patient;
import com.meddjamm.sn.remote.model.*;
import com.meddjamm.sn.services.PatientService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiagnosticPrincipalAssembler {

    private final PatientService patientService;

    private final PatientAssembler patientAssembler;

    private final CritereUtiliseAssembler critereUtiliseAssembler;

    private final MaladieAssembler maladieAssembler;

    private final ClassificationAssembler classificationAssembler;

    public DiagnosticPrincipalAssembler(PatientService patientService,
                                        PatientAssembler patientAssembler,
                                        CritereUtiliseAssembler critereUtiliseAssembler,
                                        MaladieAssembler maladieAssembler,
                                        ClassificationAssembler classificationAssembler) {
        this.patientService = patientService;
        this.patientAssembler = patientAssembler;
        this.critereUtiliseAssembler = critereUtiliseAssembler;
        this.maladieAssembler = maladieAssembler;
        this.classificationAssembler = classificationAssembler;
    }

    public List<DiagnosticPrincipalDetailDs> assembleEntitiesFrom(List<DiagnosticPrincipal> diagnosticPrincipals) {
        return diagnosticPrincipals.stream().map(this::assemblesEntityToDs).toList();
    }

    public DiagnosticPrincipalDs assembleEntityToDs(DiagnosticPrincipal diagnosticPrincipal) {
        DiagnosticPrincipalDs diagnosticPrincipalDs = new DiagnosticPrincipalDs();
        diagnosticPrincipalDs.setId(diagnosticPrincipal.getId());
        diagnosticPrincipalDs.setScoreObtenu(diagnosticPrincipal.getScoreObtenu());
        diagnosticPrincipalDs.setIndexPatient(diagnosticPrincipal.getIndexPatient());
        diagnosticPrincipalDs.setCreateDate(diagnosticPrincipal.getCreateDate());
        diagnosticPrincipalDs.setActif(diagnosticPrincipal.isActif());
        diagnosticPrincipalDs.setClassificationDs(classificationAssembler.assembleEntityToDs(diagnosticPrincipal.getClassification()));
        diagnosticPrincipalDs.setMaladieDs(maladieAssembler.assembleEntityToDs(diagnosticPrincipal.getMaladie()));
        diagnosticPrincipalDs.setCritereUtiliseDsList(critereUtiliseAssembler.createListCritereUtiliseDs(diagnosticPrincipal.getCritereUtilises()));
        return diagnosticPrincipalDs;
    }

    public DiagnosticPrincipal assembleDiagnosticPrincipalFromDs(DiagnosticPrincipalDs diagnosticPrincipalDs) {
        DiagnosticPrincipal diagnosticPrincipal = new DiagnosticPrincipal();
        diagnosticPrincipal.setId(diagnosticPrincipalDs.getId());
        diagnosticPrincipal.setScoreObtenu(diagnosticPrincipalDs.getScoreObtenu());
        diagnosticPrincipal.setIndexPatient(diagnosticPrincipalDs.getIndexPatient());
        diagnosticPrincipal.setCreateDate(diagnosticPrincipalDs.getCreateDate());
        diagnosticPrincipal.setActif(diagnosticPrincipalDs.isActif());
        diagnosticPrincipal.setClassification(classificationAssembler.assembleClassificationFromDs(diagnosticPrincipalDs.getClassificationDs()));
        diagnosticPrincipal.setMaladie(maladieAssembler.assembleMaladieFromDs(diagnosticPrincipalDs.getMaladieDs()));
        diagnosticPrincipal.setCritereUtilises(critereUtiliseAssembler.createSetCritereUtilise(diagnosticPrincipalDs.getCritereUtiliseDsList()));
        return diagnosticPrincipal;
    }

    public DiagnosticPrincipalDetailDs assemblesEntityToDs(DiagnosticPrincipal diagnosticPrincipal) {
        DiagnosticPrincipalDetailDs diagnosticAssocieDetailDs = new DiagnosticPrincipalDetailDs();
        diagnosticAssocieDetailDs.setId(diagnosticPrincipal.getId());
        diagnosticAssocieDetailDs.setScoreObtenu(diagnosticPrincipal.getScoreObtenu());
        diagnosticAssocieDetailDs.setIndexPatient(diagnosticPrincipal.getIndexPatient());
        diagnosticAssocieDetailDs.setCreateDate(diagnosticPrincipal.getCreateDate());
        if (diagnosticPrincipal.getIndexPatient() != null) {
            Patient patient = patientService.findByIndex(diagnosticPrincipal.getIndexPatient());
            PatientDetailDs patientDetailDs = patientAssembler.assemblePatientDetails(patient);
            diagnosticAssocieDetailDs.setIndexPatient(patient.getIndex());
            diagnosticAssocieDetailDs.setPatientDetailDs(patientDetailDs);
        }
        diagnosticAssocieDetailDs.setActif(diagnosticPrincipal.isActif());
        diagnosticAssocieDetailDs.setClassificationDs(classificationAssembler.assembleEntityToDs(diagnosticPrincipal.getClassification()));
        diagnosticAssocieDetailDs.setMaladieDs(maladieAssembler.assembleEntityToDs(diagnosticPrincipal.getMaladie()));
        diagnosticAssocieDetailDs.setCritereUtiliseDsList(critereUtiliseAssembler.createListCritereUtiliseDs(diagnosticPrincipal.getCritereUtilises()));
        return diagnosticAssocieDetailDs;
    }

}
