package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.ExamenBiologique;
import com.meddjamm.sn.dossiermedical.remote.model.ExamenBiologiqueDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamenBiologiqueAssembler {

    private final UtilisateurService utilisateurService;

    public ExamenBiologiqueAssembler(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public List<ExamenBiologiqueDs> assembleEntitiesFrom(List<ExamenBiologique> examenBiologiques) {
        return examenBiologiques.stream().map(this::assembleEntityToDs).toList();
    }

    public ExamenBiologiqueDs assembleEntityToDs(ExamenBiologique examenBiologique) {
        ExamenBiologiqueDs examenBiologiqueDs = new ExamenBiologiqueDs();
        if (examenBiologique.getId() != null)
            examenBiologiqueDs.setId(examenBiologique.getId());
        examenBiologiqueDs.setActif(examenBiologique.isActif());
        examenBiologiqueDs.setBiologie(examenBiologique.getBiologie());
        examenBiologiqueDs.setBiologieFileName(examenBiologique.getBiologieFileName());
        examenBiologiqueDs.setImmunologie(examenBiologique.getImmunologie());
        examenBiologiqueDs.setImmunologieFileName(examenBiologique.getImmunologieFileName());
        examenBiologiqueDs.setImagerie(examenBiologique.getImagerie());
        examenBiologiqueDs.setImagerieFileName(examenBiologique.getImagerieFileName());
        examenBiologiqueDs.setAnatomopathologie(examenBiologique.getAnatomopathologie());
        examenBiologiqueDs.setAnatomopathologieFileName(examenBiologique.getAnatomopathologieFileName());
        if (examenBiologique.getCreatedByUser() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(examenBiologique.getCreatedByUser());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            examenBiologiqueDs.setNomCompletAgent(nomAgent);
        }
        return examenBiologiqueDs;
    }

    public ExamenBiologique assembleExamenBiologiqueFromDs(ExamenBiologiqueDs examenBiologiqueDs) {
        ExamenBiologique examenBiologique = new ExamenBiologique();
        if (examenBiologiqueDs.getId() != null)
            examenBiologique.setId(examenBiologiqueDs.getId());
        examenBiologique.setActif(examenBiologiqueDs.isActif());
        examenBiologique.setBiologie(examenBiologiqueDs.getBiologie());
        examenBiologique.setBiologieFileName(examenBiologiqueDs.getBiologieFileName());
        examenBiologique.setImmunologie(examenBiologiqueDs.getImmunologie());
        examenBiologique.setImmunologieFileName(examenBiologiqueDs.getImmunologieFileName());
        examenBiologique.setImagerie(examenBiologiqueDs.getImagerie());
        examenBiologique.setImagerieFileName(examenBiologiqueDs.getImagerieFileName());
        examenBiologique.setAnatomopathologie(examenBiologiqueDs.getAnatomopathologie());
        examenBiologique.setAnatomopathologieFileName(examenBiologiqueDs.getAnatomopathologieFileName());
        return examenBiologique;
    }

    public ExamenBiologique assembleUpdateExamenBiologiqueFromDs(ExamenBiologique examenBiologique, ExamenBiologiqueDs examenBiologiqueDs) {
        examenBiologique.setBiologie(examenBiologiqueDs.getBiologie());
        examenBiologique.setBiologieFileName(examenBiologiqueDs.getBiologieFileName());
        examenBiologique.setImmunologie(examenBiologiqueDs.getImmunologie());
        examenBiologique.setImmunologieFileName(examenBiologiqueDs.getImmunologieFileName());
        examenBiologique.setImagerie(examenBiologiqueDs.getImagerie());
        examenBiologique.setImagerieFileName(examenBiologiqueDs.getImagerieFileName());
        examenBiologique.setAnatomopathologie(examenBiologiqueDs.getAnatomopathologie());
        examenBiologique.setAnatomopathologieFileName(examenBiologiqueDs.getAnatomopathologieFileName());
        return examenBiologique;
    }
}
