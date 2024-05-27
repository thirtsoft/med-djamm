package com.meddjamm.sn.rh.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import com.meddjamm.sn.rh.entity.CodagePatient;
import com.meddjamm.sn.rh.remote.model.CodagePatientDetailDs;
import com.meddjamm.sn.rh.remote.model.CodagePatientDs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CodagePatientAssembler {

    private final PatientService patientService;

    private final UtilisateurService utilisateurService;

    public List<CodagePatientDs> assembleEntitiesFrom(List<CodagePatient> codagePatientList) {
        return codagePatientList.stream().map(this::assembleEntityToDs).toList();
    }

    public CodagePatientDs assembleEntityToDs(CodagePatient codagePatient) {
        CodagePatientDs codagePatientDs = new CodagePatientDs();
        if (codagePatient.getId() != null)
            codagePatientDs.setId(codagePatient.getId());
        codagePatientDs.setPatientId(codagePatient.getPatientId());
        codagePatientDs.setEpistemologies(new ArrayList<>(codagePatient.getEpistemologies()));
        codagePatientDs.setCliniques(new ArrayList<>(codagePatient.getCliniques()));
        codagePatientDs.setExamenComplementaires(new ArrayList<>(codagePatient.getExamenComplementaires()));
        codagePatientDs.setTraitements(new ArrayList<>(codagePatient.getTraitements()));
        codagePatientDs.setSyntheses(new ArrayList<>(codagePatient.getSyntheses()));
        codagePatientDs.setActif(codagePatient.isActif());
        return codagePatientDs;
    }

    public CodagePatient assembleCodagePatientFromDs(CodagePatientDs codagePatientDs) {
        CodagePatient codagePatient = new CodagePatient();
        if (codagePatientDs.getId() != null)
            codagePatient.setId(codagePatientDs.getId());
        codagePatient.setPatientId(codagePatientDs.getPatientId());
        codagePatient.setEpistemologies(new HashSet<>(codagePatientDs.getEpistemologies()));
        codagePatient.setCliniques(new HashSet<>(codagePatientDs.getCliniques()));
        codagePatient.setExamenComplementaires(new HashSet<>(codagePatientDs.getExamenComplementaires()));
        codagePatient.setTraitements(new HashSet<>(codagePatientDs.getTraitements()));
        codagePatient.setSyntheses(new HashSet<>(codagePatientDs.getSyntheses()));
        return codagePatient;
    }

    public List<CodagePatientDetailDs> assembleEntitiesFromCodagePatientDetail(List<CodagePatient> codagePatientList) {
        return codagePatientList.stream().map(this::assembleEntityToCodagePatientDetailDs).toList();
    }

    public CodagePatientDetailDs assembleEntityToCodagePatientDetailDs(CodagePatient codagePatient) {
        CodagePatientDetailDs codagePatientDetailDs = new CodagePatientDetailDs();
        if (codagePatient.getId() != null)
            codagePatientDetailDs.setId(codagePatient.getId());
        if (codagePatient.getPatientId() != null) {
            Patient patient = patientService.findById(codagePatient.getPatientId());
            String nomComplet = patient.getPrenom() + ' ' + patient.getNom();
            codagePatientDetailDs.setPatient(nomComplet);
        }
        if (codagePatient.getCreatedByUser() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(codagePatient.getCreatedByUser());
            String nomComplet = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            codagePatientDetailDs.setNomCompletAgent(nomComplet);
        }
        codagePatientDetailDs.setEpistemologies(new ArrayList<>(codagePatient.getEpistemologies()));
        codagePatientDetailDs.setCliniques(new ArrayList<>(codagePatient.getCliniques()));
        codagePatientDetailDs.setExamenComplementaires(new ArrayList<>(codagePatient.getExamenComplementaires()));
        codagePatientDetailDs.setTraitements(new ArrayList<>(codagePatient.getTraitements()));
        codagePatientDetailDs.setSyntheses(new ArrayList<>(codagePatient.getSyntheses()));
        codagePatientDetailDs.setActif(codagePatient.isActif());
        codagePatientDetailDs.setDateAjout(codagePatient.getCreationDate());
        return codagePatientDetailDs;
    }
}
