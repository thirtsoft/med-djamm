package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.ExamenComplementaire;
import com.meddjamm.sn.dossiermedical.remote.model.ExamenComplementaireDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.ExamenComplementaireDs;
import com.meddjamm.sn.dossiermedical.repository.ExamenComplementaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExamenComplementaireAssembler {

    private final UtilisateurService utilisateurService;
    private final ExamenComplementaireRepository examenComplementaireRepository;

    public List<ExamenComplementaireDetailDs> assembleEntitiesFrom(List<ExamenComplementaire> examenComplementaires) {
        return examenComplementaires.stream().map(this::assembleEntitiesToDs).toList();
    }

    public List<ExamenComplementaire> assembleEntitiesFromDs(List<ExamenComplementaireDs> examenComplementaireDs) {
        return examenComplementaireDs.stream().map(this::assembleExamenComplementaireFromDs).toList();
    }

    public List<ExamenComplementaireDs> assembleEntitiesFromEntities(List<ExamenComplementaire> examenComplementaires) {
        return examenComplementaires.stream().map(this::assembleEntityToDs).toList();
    }

    public ExamenComplementaireDs assembleEntityToDs(ExamenComplementaire examenComplementaire) {
        ExamenComplementaireDs examenComplementaireDs = new ExamenComplementaireDs();
        if (examenComplementaire.getId() != null)
            examenComplementaireDs.setId(examenComplementaire.getId());
        examenComplementaireDs.setActif(examenComplementaire.isActif());
        examenComplementaireDs.setCreatedDate(examenComplementaire.getCreatedDate());
        examenComplementaireDs.setBiologie(examenComplementaire.getBiologie());
        examenComplementaireDs.setBiologieFileName(examenComplementaire.getBiologieFileName());
        examenComplementaireDs.setImmunologie(examenComplementaire.getImmunologie());
        examenComplementaireDs.setImmunologieFileName(examenComplementaire.getImmunologieFileName());
        examenComplementaireDs.setImagerie(examenComplementaire.getImagerie());
        examenComplementaireDs.setImagerieFileName(examenComplementaire.getImagerieFileName());
        examenComplementaireDs.setAnatomopathologie(examenComplementaire.getAnatomopathologie());
        examenComplementaireDs.setAnatomopathologieFileName(examenComplementaire.getAnatomopathologieFileName());
        examenComplementaireDs.setCircuitPatientId(examenComplementaire.getCircuitPatientId());
        examenComplementaireDs.setCreatedBy(examenComplementaire.getCreatedBy());
        if (examenComplementaire.getCreatedBy() != null) {
            Utilisateur utilisateur = utilisateurService.findUserById(examenComplementaire.getCreatedBy());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            examenComplementaireDs.setNomCompletAgent(nomAgent);
        }
        return examenComplementaireDs;
    }

    public ExamenComplementaire assembleExamenComplementaireFromDs(ExamenComplementaireDs examenComplementaireDs) {
        ExamenComplementaire examenComplementaire = new ExamenComplementaire();
        if (examenComplementaireDs.getId() != null)
            examenComplementaire.setId(examenComplementaireDs.getId());
        examenComplementaire.setActif(examenComplementaireDs.isActif());
        examenComplementaire.setCreatedDate(examenComplementaireDs.getCreatedDate());
        examenComplementaire.setBiologie(examenComplementaireDs.getBiologie());
        examenComplementaire.setBiologieFileName(examenComplementaireDs.getBiologieFileName());
        examenComplementaire.setImmunologie(examenComplementaireDs.getImmunologie());
        examenComplementaire.setImmunologieFileName(examenComplementaireDs.getImmunologieFileName());
        examenComplementaire.setImagerie(examenComplementaireDs.getImagerie());
        examenComplementaire.setImagerieFileName(examenComplementaireDs.getImagerieFileName());
        examenComplementaire.setAnatomopathologie(examenComplementaireDs.getAnatomopathologie());
        examenComplementaire.setAnatomopathologieFileName(examenComplementaireDs.getAnatomopathologieFileName());
        examenComplementaire.setCircuitPatientId(examenComplementaireDs.getCircuitPatientId());
        examenComplementaire.setCreatedBy(examenComplementaireDs.getCreatedBy());
        return examenComplementaire;
    }

    public ExamenComplementaire assembleUpdateExamenComplementaireFromDs(ExamenComplementaire examenComplementaire, ExamenComplementaireDs examenComplementaireDs) {
        examenComplementaire.setBiologie(examenComplementaireDs.getBiologie());
        examenComplementaire.setBiologieFileName(examenComplementaireDs.getBiologieFileName());
        examenComplementaire.setImmunologie(examenComplementaireDs.getImmunologie());
        examenComplementaire.setImmunologieFileName(examenComplementaireDs.getImmunologieFileName());
        examenComplementaire.setImagerie(examenComplementaireDs.getImagerie());
        examenComplementaire.setImagerieFileName(examenComplementaireDs.getImagerieFileName());
        examenComplementaire.setAnatomopathologie(examenComplementaireDs.getAnatomopathologie());
        examenComplementaire.setAnatomopathologieFileName(examenComplementaireDs.getAnatomopathologieFileName());
        return examenComplementaire;
    }

    public ExamenComplementaireDetailDs assembleEntitiesToDs(ExamenComplementaire examenComplementaire) {
        ExamenComplementaireDetailDs examenComplementaireDs = new ExamenComplementaireDetailDs();
        if (examenComplementaire.getId() != null)
            examenComplementaireDs.setId(examenComplementaire.getId());
        examenComplementaireDs.setActif(examenComplementaire.isActif());
        examenComplementaireDs.setCreatedDate(examenComplementaire.getCreatedDate());
        examenComplementaireDs.setBiologie(examenComplementaire.getBiologie());
        examenComplementaireDs.setBiologieFileName(examenComplementaire.getBiologieFileName());
        examenComplementaireDs.setImmunologie(examenComplementaire.getImmunologie());
        examenComplementaireDs.setImmunologieFileName(examenComplementaire.getImmunologieFileName());
        examenComplementaireDs.setImagerie(examenComplementaire.getImagerie());
        examenComplementaireDs.setImagerieFileName(examenComplementaire.getImagerieFileName());
        examenComplementaireDs.setAnatomopathologie(examenComplementaire.getAnatomopathologie());
        examenComplementaireDs.setAnatomopathologieFileName(examenComplementaire.getAnatomopathologieFileName());
        examenComplementaireDs.setCircuitPatientId(examenComplementaire.getCircuitPatientId());
        examenComplementaireDs.setCreatedBy(examenComplementaire.getCreatedBy());
        if (examenComplementaire.getCreatedBy() != null) {
            Utilisateur utilisateur = utilisateurService.findUserById(examenComplementaire.getCreatedBy());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            examenComplementaireDs.setNomCompletAgent(nomAgent);
        }
        return examenComplementaireDs;
    }

}