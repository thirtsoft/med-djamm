package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.AvisSpecialiste;
import com.meddjamm.sn.dossiermedical.remote.model.AllCircuitPatientDs;
import com.meddjamm.sn.dossiermedical.remote.model.AvisSpecialisteDs;
import com.meddjamm.sn.utils.UtilString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AvisSpecialisteAssembler {

    private final UtilisateurService utilisateurService;

    public AvisSpecialisteAssembler(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public List<AvisSpecialisteDs> assembleEntitiesFrom(List<AvisSpecialiste> avisSpecialisteList) {
        return avisSpecialisteList.stream().map(this::assembleEntityToDs).toList();
    }

    public AvisSpecialisteDs assembleEntityToDs(AvisSpecialiste avisSpecialiste) {
        AvisSpecialisteDs avisSpecialisteDs = new AvisSpecialisteDs();
        if (avisSpecialiste.getId() != null)
            avisSpecialisteDs.setId(avisSpecialiste.getId());
        avisSpecialisteDs.setActif(avisSpecialiste.isActif());
        avisSpecialisteDs.setCreatedDate(avisSpecialiste.getCreatedDate());
        avisSpecialisteDs.setResume(avisSpecialiste.getResume());
        avisSpecialisteDs.setCreatedBy(avisSpecialiste.getCreatedBy());
        avisSpecialisteDs.setCircuitPatientId(avisSpecialiste.getCircuitPatientId());
        if (avisSpecialiste.getCreatedBy() != null) {
            Utilisateur utilisateur = utilisateurService.findUserById(avisSpecialiste.getCreatedBy());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            avisSpecialisteDs.setNomCompletAgent(nomAgent);
        }
        return avisSpecialisteDs;
    }

    public AvisSpecialiste assembleAvisSpecialisteFromDs(AvisSpecialisteDs avisSpecialisteDs) {
        AvisSpecialiste avisSpecialiste = new AvisSpecialiste();
        if (avisSpecialisteDs.getId() != null)
            avisSpecialiste.setId(avisSpecialisteDs.getId());
        avisSpecialiste.setActif(avisSpecialisteDs.isActif());
        avisSpecialiste.setCreatedDate(avisSpecialisteDs.getCreatedDate());
        avisSpecialiste.setResume(avisSpecialisteDs.getResume());
        avisSpecialiste.setCreatedBy(avisSpecialisteDs.getCreatedBy());
        avisSpecialiste.setCircuitPatientId(avisSpecialisteDs.getCircuitPatientId());
        return avisSpecialiste;
    }

    public List<AllCircuitPatientDs> assembleAllCircuitPatientEntitiesFrom(List<AvisSpecialiste> avisSpecialisteList) {
        return avisSpecialisteList.stream().map(this::assembleAllCircuitPatientDsFromEntity).toList();
    }

    public AllCircuitPatientDs assembleAllCircuitPatientDsFromEntity(AvisSpecialiste avisSpecialiste) {
        AllCircuitPatientDs allCircuitPatientDs = new AllCircuitPatientDs();
        if (avisSpecialiste.getId() != null)
            allCircuitPatientDs.setId(avisSpecialiste.getId());
        allCircuitPatientDs.setActif(avisSpecialiste.isActif());
        allCircuitPatientDs.setNumeroCircuit(UtilString.createNumeroCircuitPatient(
                avisSpecialiste.getCircuitPatient().getNumeroCircuit()));
        allCircuitPatientDs.setCreateDate(avisSpecialiste.getCreatedDate());
        allCircuitPatientDs.setType("Avis spécialiste");
        allCircuitPatientDs.setCode(avisSpecialiste.getResume());
        if (avisSpecialiste.getCreatedByUser() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(avisSpecialiste.getCreatedByUser());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            allCircuitPatientDs.setNomCompletAgent(nomAgent);
        }
        return allCircuitPatientDs;
    }
}