package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.Synthese;
import com.meddjamm.sn.dossiermedical.remote.model.SyntheseDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SyntheseAssembler {

    private final UtilisateurService utilisateurService;

    public SyntheseAssembler(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public List<SyntheseDs> assembleEntitiesFrom(List<Synthese> syntheseList) {
        return syntheseList.stream().map(this::assembleEntityToDs).toList();
    }

    public List<Synthese> assembleEntitiesFromDs(List<SyntheseDs> syntheseList) {
        return syntheseList.stream().map(this::assembleSyntheseFromDs).toList();
    }

    public SyntheseDs assembleEntityToDs(Synthese synthese) {
        SyntheseDs syntheseDs = new SyntheseDs();
        if (synthese.getId() != null)
            syntheseDs.setId(synthese.getId());
        syntheseDs.setActif(synthese.isActif());
        syntheseDs.setObservation(synthese.getObservation());
        if (synthese.getCreatedByUser() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(synthese.getCreatedByUser());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            syntheseDs.setNomCompletAgent(nomAgent);
        }
        return syntheseDs;
    }

    public Synthese assembleSyntheseFromDs(SyntheseDs syntheseDs) {
        Synthese synthese = new Synthese();
        if (syntheseDs.getId() != null)
            synthese.setId(syntheseDs.getId());
        synthese.setActif(syntheseDs.isActif());
        synthese.setObservation(syntheseDs.getObservation());
        return synthese;
    }

    public Synthese assembleUpdateSyntheseFromDs(Synthese synthese, SyntheseDs syntheseDs) {
        synthese.setObservation(syntheseDs.getObservation());
        return synthese;
    }
}
