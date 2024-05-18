package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.ExamenComplementaire;
import com.meddjamm.sn.dossiermedical.remote.model.ExamenComplementaireDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.ExamenComplementaireDs;
import com.meddjamm.sn.rh.piecejointe.PiecesJointesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExamenComplementaireAssembler {

    private final UtilisateurService utilisateurService;
    private final PiecesJointesService piecesJointesService;

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
        examenComplementaireDs.setBiologie(examenComplementaire.getBiologie());
        examenComplementaireDs.setBiologieFileName(examenComplementaire.getBiologieFileName());
        examenComplementaireDs.setImmunologie(examenComplementaire.getImmunologie());
        examenComplementaireDs.setImmunologieFileName(examenComplementaire.getImmunologieFileName());
        examenComplementaireDs.setImagerie(examenComplementaire.getImagerie());
        examenComplementaireDs.setImagerieFileName(examenComplementaire.getImagerieFileName());
        examenComplementaireDs.setAnatomopathologie(examenComplementaire.getAnatomopathologie());
        examenComplementaireDs.setAnatomopathologieFileName(examenComplementaire.getAnatomopathologieFileName());
        if (examenComplementaire.getCreatedByUser() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(examenComplementaire.getCreatedByUser());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            examenComplementaireDs.setNomCompletAgent(nomAgent);
        }
        examenComplementaireDs.setPiecesJointesDsList(piecesJointesService.getListPieceJointePatient(examenComplementaire.getId()));
        return examenComplementaireDs;
    }

    public ExamenComplementaire assembleExamenComplementaireFromDs(ExamenComplementaireDs examenComplementaireDs) {
        ExamenComplementaire examenComplementaire = new ExamenComplementaire();
        if (examenComplementaireDs.getId() != null)
            examenComplementaire.setId(examenComplementaireDs.getId());
        examenComplementaire.setActif(examenComplementaireDs.isActif());
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
        examenComplementaireDs.setBiologie(examenComplementaire.getBiologie());
        examenComplementaireDs.setBiologieFileName(examenComplementaire.getBiologieFileName());
        examenComplementaireDs.setImmunologie(examenComplementaire.getImmunologie());
        examenComplementaireDs.setImmunologieFileName(examenComplementaire.getImmunologieFileName());
        examenComplementaireDs.setImagerie(examenComplementaire.getImagerie());
        examenComplementaireDs.setImagerieFileName(examenComplementaire.getImagerieFileName());
        examenComplementaireDs.setAnatomopathologie(examenComplementaire.getAnatomopathologie());
        examenComplementaireDs.setAnatomopathologieFileName(examenComplementaire.getAnatomopathologieFileName());
        if (examenComplementaire.getCreatedByUser() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(examenComplementaire.getCreatedByUser());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            examenComplementaireDs.setNomCompletAgent(nomAgent);
        }
        return examenComplementaireDs;
    }

}