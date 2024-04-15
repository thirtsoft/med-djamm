package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.assembler.UtilisateurAssembler;
import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.entity.RendezVous;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.RendezVousDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.RendezVousDs;
import com.meddjamm.sn.dossiermedical.services.PatientService;
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

    public List<RendezVousDetailDs> assembleEntitiesFrom(List<RendezVous> rendezVous) {
        return rendezVous.stream().map(this::assembleEntitiesToDs).toList();
    }

    public RendezVousDs assembleEntityToDs(RendezVous rendezVous) {
        RendezVousDs rendezVousDs = new RendezVousDs();
        rendezVousDs.setLibelle(rendezVous.getLibelle());
        rendezVousDs.setCode(rendezVous.getCode());
        rendezVousDs.setMatricule(rendezVous.getMatricule());
        rendezVousDs.setCreatedBy(rendezVous.getCreatedBy());
        rendezVousDs.setEtat(rendezVous.getEtat());
        rendezVousDs.setDateRendezVous(rendezVous.getDateRendezVous());
        rendezVousDs.setHeure(rendezVous.getHeure());
        rendezVousDs.setCreateDate(rendezVous.getCreateDate());
        rendezVousDs.setActif(rendezVous.isActif());
        if (rendezVous.getCreatedBy() != null) {
            Utilisateur utilisateur = utilisateurService.findUserById(rendezVous.getCreatedBy());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            rendezVousDs.setNomCompletAgent(nomAgent);
        }
        return rendezVousDs;
    }

    public RendezVous assembleRendezVoRendezVoususFromDs(RendezVousDs rendezVousDs) {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setCode(rendezVousDs.getCode());
        rendezVous.setLibelle(rendezVousDs.getLibelle());
        rendezVous.setMatricule(rendezVousDs.getMatricule());
        rendezVous.setCreatedBy(rendezVousDs.getCreatedBy());
        rendezVous.setDateRendezVous(rendezVousDs.getDateRendezVous());
        rendezVous.setHeure(rendezVousDs.getHeure());
        rendezVous.setCreateDate(rendezVousDs.getCreateDate());
        rendezVous.setActif(rendezVousDs.isActif());
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
        rendezVousDs.setCode(rendezVous.getCode());
        rendezVousDs.setMatricule(rendezVous.getMatricule());
        if (rendezVous.getCode() != null) {
            Patient patient = patientService.findByCode(rendezVous.getCode());
            PatientDetailDs patientDetailDs = patientAssembler.assemblePatientDetails(patient);
            rendezVousDs.setPatient(patientDetailDs);
        }
        if (rendezVous.getCreatedBy() != null) {
            Utilisateur utilisateur = utilisateurService.findUserById(rendezVous.getCreatedBy());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            rendezVousDs.setNomCompletAgent(nomAgent);
        }
        if (rendezVous.getMatricule() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(rendezVous.getMatricule());
            UtilisateurDs utilisateurDs = utilisateurAssembler.assembleUtilisateurDsFromEntity(utilisateur);
            rendezVousDs.setUtilisateurDs(utilisateurDs);
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