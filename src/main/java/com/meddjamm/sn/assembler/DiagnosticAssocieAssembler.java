package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.DiagnosticAssocie;
import com.meddjamm.sn.entity.Patient;
import com.meddjamm.sn.remote.model.DiagnosticAssocieDetailDs;
import com.meddjamm.sn.remote.model.DiagnosticAssocieDs;
import com.meddjamm.sn.remote.model.PatientDetailDs;
import com.meddjamm.sn.services.PatientService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiagnosticAssocieAssembler {

    private final PatientService patientService;

    private final PatientAssembler patientAssembler;

    public DiagnosticAssocieAssembler(PatientService patientService, PatientAssembler patientAssembler) {
        this.patientService = patientService;
        this.patientAssembler = patientAssembler;
    }

    public List<DiagnosticAssocieDetailDs> assembleEntitiesFrom(List<DiagnosticAssocie> diagnosticAssocies) {
        return diagnosticAssocies.stream().map(this::assemblesEntityToDs).toList();
    }

    public DiagnosticAssocieDs assembleEntityToDs(DiagnosticAssocie diagnosticAssocie) {
        DiagnosticAssocieDs diagnosticAssocieDs = new DiagnosticAssocieDs();
        diagnosticAssocieDs.setLibelle(diagnosticAssocie.getLibelle());
        diagnosticAssocieDs.setIndexPatient(diagnosticAssocie.getIndexPatient());
        diagnosticAssocieDs.setActif(diagnosticAssocie.isActif());
        return diagnosticAssocieDs;
    }

    public DiagnosticAssocie assembleDiagnosticAssocieFromDs(DiagnosticAssocieDs diagnosticAssocieDs) {
        DiagnosticAssocie diagnosticAssocie = new DiagnosticAssocie();
        diagnosticAssocie.setLibelle(diagnosticAssocieDs.getLibelle());
        diagnosticAssocie.setIndexPatient(diagnosticAssocieDs.getIndexPatient());
        diagnosticAssocie.setActif(diagnosticAssocieDs.isActif());
        return diagnosticAssocie;
    }

    public DiagnosticAssocieDetailDs assemblesEntityToDs(DiagnosticAssocie diagnosticAssocie) {
        DiagnosticAssocieDetailDs diagnosticAssocieDetailDs = new DiagnosticAssocieDetailDs();
        diagnosticAssocieDetailDs.setId(diagnosticAssocie.getId());
        diagnosticAssocieDetailDs.setLibelle(diagnosticAssocie.getLibelle());
        if (diagnosticAssocie.getIndexPatient() != null) {
            Patient patient = patientService.findByIndex(diagnosticAssocie.getIndexPatient());
            PatientDetailDs patientDetailDs = patientAssembler.assemblePatientDetails(patient);
            diagnosticAssocieDetailDs.setIndexPatient(patient.getIndex());
            diagnosticAssocieDetailDs.setPatient(patientDetailDs);
        }
        diagnosticAssocieDetailDs.setActif(diagnosticAssocie.isActif());
        return diagnosticAssocieDetailDs;
    }

}