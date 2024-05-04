package com.meddjamm.sn.rh.assembler;

import com.meddjamm.sn.config.assembler.UtilisateurAssembler;
import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.assembler.PatientAssembler;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import com.meddjamm.sn.rh.entity.RendezVous;
import com.meddjamm.sn.rh.remote.model.RendezVousDetailDs;
import com.meddjamm.sn.rh.remote.model.RendezVousDs;
import com.meddjamm.sn.rh.services.RendezVousService;
import com.meddjamm.sn.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RendezVousAssembler {

    private final PatientService patientService;
    private final PatientAssembler patientAssembler;
    private final UtilisateurService utilisateurService;
    private final UtilisateurAssembler utilisateurAssembler;
    private final RendezVousService rendezVousService;

    public List<RendezVousDetailDs> assembleEntitiesFrom(List<RendezVous> rendezVous) {
        return rendezVous.stream().map(this::assembleEntitiesToDs).toList();
    }

    public RendezVousDs assembleEntityToDs(RendezVous rendezVous) {
        RendezVousDs rendezVousDs = new RendezVousDs();
        rendezVousDs.setLibelle(rendezVous.getLibelle());
        rendezVousDs.setPatientId(rendezVous.getPatientId());
        rendezVousDs.setMedecinId(rendezVousDs.getMedecinId());
        rendezVousDs.setCreatedBy(rendezVous.getCreatedBy());
        rendezVousDs.setEtat(rendezVous.getEtat());
        rendezVousDs.setDateRendezVous(rendezVous.getDateRendezVous());
        rendezVousDs.setHeure(rendezVous.getHeure());
        rendezVousDs.setCreateDate(rendezVous.getCreateDate());
        rendezVousDs.setActif(rendezVous.isActif());
        if (rendezVous.getMedecinId() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurById(rendezVous.getMedecinId());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            rendezVousDs.setNomCompletAgent(nomAgent);
        }
        if (rendezVous.getPatientId() != null) {
            Patient patient = patientService.findById(rendezVous.getPatientId());
            String nomPatient = patient.getPrenom() + ' ' + patient.getNom();
            rendezVousDs.setNomCompletPatient(nomPatient);
        }
        return rendezVousDs;
    }

    public RendezVous assembleRendezVoRendezVoususFromDs(RendezVousDs rendezVousDs) {
        RendezVous rendezVous = new RendezVous();
        if (rendezVousDs.getId() != null)
            rendezVous.setId(rendezVousDs.getId());
        rendezVous.setPatientId(rendezVousDs.getPatientId());
        rendezVous.setLibelle(rendezVousDs.getLibelle());
        rendezVous.setMedecinId(rendezVousDs.getMedecinId());
        rendezVous.setCreatedBy(rendezVousDs.getCreatedBy());
        rendezVous.setDateRendezVous(rendezVousDs.getDateRendezVous());
        rendezVous.setHeure(rendezVousDs.getHeure());
        rendezVous.setCreateDate(rendezVousDs.getCreateDate());
        rendezVous.setActif(rendezVousDs.isActif());
        return rendezVous;
    }

    public RendezVous assembleUpdateRendezVousFromDs(RendezVousDs rendezVousDs) {
        RendezVous rendezVous = rendezVousService.findById(rendezVousDs.getId());
        rendezVous.setPatientId(rendezVousDs.getPatientId());
        rendezVous.setLibelle(rendezVousDs.getLibelle());
        rendezVous.setMedecinId(rendezVousDs.getMedecinId());
        rendezVous.setCreatedBy(rendezVousDs.getCreatedBy());
        rendezVous.setDateRendezVous(rendezVousDs.getDateRendezVous());
        rendezVous.setHeure(rendezVousDs.getHeure());
        return rendezVous;
    }

    public RendezVousDetailDs assembleEntitiesToDs(RendezVous rendezVous) {
        RendezVousDetailDs rendezVousDs = new RendezVousDetailDs();
        if (rendezVous.getId() != null)
            rendezVousDs.setId(rendezVous.getId());
        rendezVousDs.setCreatedBy(rendezVous.getCreatedBy());
        rendezVousDs.setLibelle(rendezVous.getLibelle());
        rendezVousDs.setDateRendezVous(rendezVous.getDateRendezVous());
        rendezVousDs.setHeure(rendezVous.getHeure());
        rendezVousDs.setCreateDate(rendezVous.getCreateDate());
        rendezVousDs.setActif(rendezVous.isActif());
        rendezVousDs.setPatientId(rendezVous.getPatientId());
        rendezVousDs.setMedecinId(rendezVous.getMedecinId());
        if (rendezVous.getPatientId() != null) {
            Patient patient = patientService.findById(rendezVous.getPatientId());
            String nomPatient = patient.getPrenom() + ' ' + patient.getNom();
            PatientDetailDs patientDetailDs = patientAssembler.assemblePatientDetails(patient);
            rendezVousDs.setPatient(patientDetailDs);
            rendezVousDs.setNomCompletPatient(nomPatient);
        }
        if (rendezVous.getCreatedByUser() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(rendezVous.getCreatedByUser());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            rendezVousDs.setNomCompletAgent(nomAgent);
        }
        if (rendezVous.getMedecinId() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurById(rendezVous.getMedecinId());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            UtilisateurDs utilisateurDs = utilisateurAssembler.assembleUtilisateurDsFromEntity(utilisateur);
            rendezVousDs.setUtilisateurDs(utilisateurDs);
            rendezVousDs.setNomCompletAgent(nomAgent);
        }
        rendezVousDs.setEtat(rendezVous.getEtat());
        rendezVousDs.setLibelleEtat(getLibelleEtat(rendezVous.getEtat()));
        return rendezVousDs;
    }

    public String getLibelleEtat(int etat) {
        if (etat == Constants.ETAT_PROGRAMME)
            return "PROGRAMME";
        else if (etat == Constants.ETAT_ACCEPTE)
            return "ACCEPTE";
        else if (etat == Constants.ETAT_EVAL_PATIENT)
            return "EVALUATION";
        else if (etat == Constants.ETAT_REJETE)
            return "REJETE";
        return "";
    }
}