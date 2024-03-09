package com.meddjamm.sn.assembler;

import com.meddjamm.sn.dossiermedical.assembler.PatientAssembler;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import com.meddjamm.sn.entity.DossierPatient;
import com.meddjamm.sn.remote.model.DossierPatientDetailDs;
import com.meddjamm.sn.remote.model.DossierPatientDs;
import com.meddjamm.sn.remote.model.DossierPatientListDs;
import com.meddjamm.sn.utils.UtilString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DossierPatientAssembler {

    private final PatientService patientService;

    private final PatientAssembler patientAssembler;

    private final DiagnosticPrincipalAssembler diagnosticPrincipalAssembler;

    private final DiagnosticAssocieAssembler diagnosticAssocieAssembler;

    private final HistoireMaladieAssembler histoireMaladieAssembler;

    private final AntecedentMedicauxAssembler antecedentMedicauxAssembler;

    private final AntecedentChirurgieAssembler antecedentChirurgieAssembler;

    private final AntecedentGynecologieAssembler antecedentGynecologieAssembler;

    private final AntecedentFamilialAssembler antecedentFamilialAssembler;

    public DossierPatientAssembler(PatientService patientService,
                                   PatientAssembler patientAssembler,
                                   DiagnosticPrincipalAssembler diagnosticPrincipalAssembler,
                                   DiagnosticAssocieAssembler diagnosticAssocieAssembler,
                                   HistoireMaladieAssembler histoireMaladieAssembler,
                                   AntecedentMedicauxAssembler antecedentMedicauxAssembler,
                                   AntecedentChirurgieAssembler antecedentChirurgieAssembler,
                                   AntecedentGynecologieAssembler antecedentGynecologieAssembler,
                                   AntecedentFamilialAssembler antecedentFamilialAssembler
    ) {
        this.patientService = patientService;
        this.patientAssembler = patientAssembler;
        this.diagnosticPrincipalAssembler = diagnosticPrincipalAssembler;
        this.diagnosticAssocieAssembler = diagnosticAssocieAssembler;
        this.histoireMaladieAssembler = histoireMaladieAssembler;
        this.antecedentMedicauxAssembler = antecedentMedicauxAssembler;
        this.antecedentChirurgieAssembler = antecedentChirurgieAssembler;
        this.antecedentGynecologieAssembler = antecedentGynecologieAssembler;
        this.antecedentFamilialAssembler = antecedentFamilialAssembler;
    }


    public List<DossierPatientDetailDs> assembleEntitiesFrom(List<DossierPatient> dossierPatients) {
        return dossierPatients.stream().map(this::assemblesEntityToDs).toList();
    }

    public DossierPatientDs assembleEntityToDs(DossierPatient dossierPatient) {
        DossierPatientDs dossierPatientDs = new DossierPatientDs();
        dossierPatientDs.setId(dossierPatient.getId());
        dossierPatientDs.setActif(dossierPatient.isActif());
        dossierPatientDs.setIndexMedecin(dossierPatient.getIndexPatient());
        dossierPatientDs.setIndexPatient(dossierPatient.getIndexPatient());
        //    dossierPatientDs.setNumeroDossier(dossierPatient.getNumeroDossier());
        dossierPatientDs.setNumeroDossier(
                UtilString.createNumeroDossierPatient(dossierPatient.getNumeroDossier()));
        dossierPatientDs.setDiagnosticPrincipalDs(diagnosticPrincipalAssembler.assembleEntityToDs(dossierPatient.getDiagnosticPrincipal()));
        dossierPatientDs.setDiagnosticAssocieDs(diagnosticAssocieAssembler.assembleEntityToDs(dossierPatient.getDiagnosticAssocie()));
        dossierPatientDs.setHistoireMaladieDs(histoireMaladieAssembler.assembleEntityToDs(dossierPatient.getHistoireMaladie()));
        dossierPatientDs.setAntecedentMedicauxDs(antecedentMedicauxAssembler.assembleEntityToDs(dossierPatient.getAntecedentMedicaux()));
        dossierPatientDs.setAntecedentChirurgieDs(antecedentChirurgieAssembler.assembleEntityToDs(dossierPatient.getAntecedentChirurgie()));
        dossierPatientDs.setAntecedentGynecologieDs(antecedentGynecologieAssembler.assembleEntityToDs(dossierPatient.getAntecedentGynecologie()));
        dossierPatientDs.setAntecedentFamilialDs(antecedentFamilialAssembler.assembleEntityToDs(dossierPatient.getAntecedentFamilial()));
        dossierPatientDs.setCreateDate(dossierPatient.getCreateDate());
        return dossierPatientDs;
    }

    public DossierPatient assembleDossierPatientFromDs(DossierPatientDs dossierPatientDs) {
        DossierPatient dossierPatient = new DossierPatient();
        dossierPatient.setId(dossierPatient.getId());
        dossierPatient.setActif(dossierPatient.isActif());
        dossierPatient.setIndexMedecin(dossierPatientDs.getIndexPatient());
        dossierPatient.setIndexPatient(dossierPatientDs.getIndexPatient());
        dossierPatient.setDiagnosticPrincipal(diagnosticPrincipalAssembler.assembleDiagnosticPrincipalFromDs(dossierPatientDs.getDiagnosticPrincipalDs()));
        dossierPatient.setDiagnosticAssocie(diagnosticAssocieAssembler.assembleDiagnosticAssocieFromDs(dossierPatientDs.getDiagnosticAssocieDs()));
        dossierPatient.setHistoireMaladie(histoireMaladieAssembler.assembleHistoireMaladieFromDs(dossierPatientDs.getHistoireMaladieDs()));
        dossierPatient.setAntecedentMedicaux(antecedentMedicauxAssembler.assembleAntecedentMedicauxFromDs(dossierPatientDs.getAntecedentMedicauxDs()));
        dossierPatient.setAntecedentChirurgie(antecedentChirurgieAssembler.assembleAntecedentChirurgieFromDs(dossierPatientDs.getAntecedentChirurgieDs()));
        dossierPatient.setAntecedentGynecologie(antecedentGynecologieAssembler.assembleAntecedentGynecologieFromDs(dossierPatientDs.getAntecedentGynecologieDs()));
        dossierPatient.setAntecedentFamilial(antecedentFamilialAssembler.assembleAntecedentFamilialFromDs(dossierPatientDs.getAntecedentFamilialDs()));
        dossierPatient.setCreateDate(dossierPatient.getCreateDate());
        return dossierPatient;
    }

    public DossierPatientDetailDs assemblesEntityToDs(DossierPatient dossierPatient) {
        DossierPatientDetailDs dossierPatientDetailDs = new DossierPatientDetailDs();
        dossierPatientDetailDs.setId(dossierPatient.getId());
        dossierPatientDetailDs.setIndexPatient(dossierPatient.getIndexPatient());
        dossierPatientDetailDs.setNumeroDossier(
                UtilString.createNumeroDossierPatient(dossierPatient.getNumeroDossier())
        );
        dossierPatientDetailDs.setActif(dossierPatient.isActif());
        dossierPatientDetailDs.setIndexMedecin(dossierPatient.getIndexPatient());
        dossierPatientDetailDs.setDiagnosticPrincipalDs(diagnosticPrincipalAssembler.assembleEntityToDs(dossierPatient.getDiagnosticPrincipal()));
        dossierPatientDetailDs.setDiagnosticAssocieDs(diagnosticAssocieAssembler.assembleEntityToDs(dossierPatient.getDiagnosticAssocie()));
        dossierPatientDetailDs.setHistoireMaladieDs(histoireMaladieAssembler.assembleEntityToDs(dossierPatient.getHistoireMaladie()));
        dossierPatientDetailDs.setAntecedentMedicauxDs(antecedentMedicauxAssembler.assembleEntityToDs(dossierPatient.getAntecedentMedicaux()));
        dossierPatientDetailDs.setAntecedentChirurgieDs(antecedentChirurgieAssembler.assembleEntityToDs(dossierPatient.getAntecedentChirurgie()));
        dossierPatientDetailDs.setAntecedentGynecologieDs(antecedentGynecologieAssembler.assembleEntityToDs(dossierPatient.getAntecedentGynecologie()));
        dossierPatientDetailDs.setAntecedentFamilialDs(antecedentFamilialAssembler.assembleEntityToDs(dossierPatient.getAntecedentFamilial()));
        dossierPatientDetailDs.setCreateDate(dossierPatient.getCreateDate());
        if (dossierPatient.getIndexPatient() != null) {
            Patient patient = patientService.findByCode(dossierPatient.getIndexPatient());
            PatientDetailDs patientDetailDs = patientAssembler.assemblePatientDetails(patient);
            dossierPatientDetailDs.setIndexPatient(patient.getCode());
            dossierPatientDetailDs.setPatientDetailDs(patientDetailDs);
        }
        return dossierPatientDetailDs;
    }

    public DossierPatientListDs createDossierPatientListDs(DossierPatient dossierPatient) {
        DossierPatientListDs dto = new DossierPatientListDs();
        dto.setId(dossierPatient.getId());
        //    dto.setNumeroDossier(dossierPatient.getNumeroDossier());
        dto.setNumeroDossier(UtilString.createNumeroDossierPatient(dossierPatient.getNumeroDossier()));
        dto.setCreateDate(dossierPatient.getCreateDate());
        if (dossierPatient.getIndexPatient() != null) {
            Patient patient = patientService.findByCode(dossierPatient.getIndexPatient());
            dto.setPatient(patient.getPrenom() + " " + patient.getNom());
        }
        return dto;
    }

    public List<DossierPatientListDs> createListeDossierPatient(List<DossierPatient> dossierPatients) {
        return dossierPatients.stream().map(this::createDossierPatientListDs).toList();
    }

}
