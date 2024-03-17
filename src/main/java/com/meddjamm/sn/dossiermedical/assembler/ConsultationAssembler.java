package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.Consultation;
import com.meddjamm.sn.dossiermedical.remote.model.ConsultationDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultationAssembler {

    private final UtilisateurService utilisateurService;

    public ConsultationAssembler(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public List<ConsultationDs> assembleEntitiesFrom(List<Consultation> consultationList) {
        return consultationList.stream().map(this::assembleEntityToDs).toList();
    }

    public ConsultationDs assembleEntityToDs(Consultation consultation) {
        ConsultationDs consultationDs = new ConsultationDs();
        if (consultation.getId() != null)
            consultationDs.setId(consultation.getId());
        consultationDs.setActif(consultation.isActif());
        consultationDs.setCreatedDate(consultation.getCreatedDate());
        consultationDs.setResume(consultation.getResume());
        consultationDs.setCreatedBy(consultation.getCreatedBy());
        consultationDs.setCircuitPatientId(consultation.getCircuitPatientId());
        if (consultation.getCreatedBy() != null) {
            Utilisateur utilisateur = utilisateurService.findUserById(consultation.getCreatedBy());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            consultationDs.setNomCompletAgent(nomAgent);
        }
        return consultationDs;
    }

    public Consultation assembleConsultationFromDs(ConsultationDs consultationDs) {
        Consultation consultation = new Consultation();
        if (consultation.getId() != null)
            consultation.setId(consultationDs.getId());
        consultation.setActif(consultationDs.isActif());
        consultation.setCreatedDate(consultationDs.getCreatedDate());
        consultation.setResume(consultationDs.getResume());
        consultation.setCreatedBy(consultationDs.getCreatedBy());
        consultation.setCircuitPatientId(consultationDs.getCircuitPatientId());
        return consultation;
    }

}