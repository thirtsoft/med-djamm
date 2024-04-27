package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.Synthese;
import com.meddjamm.sn.dossiermedical.remote.model.SyntheseDs;
import com.meddjamm.sn.dossiermedical.repository.SyntheseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SyntheseAssembler {

    private final UtilisateurService utilisateurService;
    private final SyntheseRepository syntheseRepository;

    public SyntheseAssembler(UtilisateurService utilisateurService,
                             SyntheseRepository syntheseRepository) {
        this.utilisateurService = utilisateurService;
        this.syntheseRepository = syntheseRepository;
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
        syntheseDs.setCreatedDate(synthese.getCreatedDate());
        syntheseDs.setObservation(synthese.getObservation());
        syntheseDs.setCreatedBy(synthese.getCreatedBy());
        syntheseDs.setCircuitPatientId(synthese.getCircuitPatientId());
        if (synthese.getCreatedBy() != null) {
            Utilisateur utilisateur = utilisateurService.findUserById(synthese.getCreatedBy());
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
        synthese.setCreatedDate(syntheseDs.getCreatedDate());
        synthese.setObservation(syntheseDs.getObservation());
        synthese.setCreatedBy(syntheseDs.getCreatedBy());
        synthese.setCircuitPatientId(syntheseDs.getCircuitPatientId());
        return synthese;
    }

    public Synthese assembleUpdateSyntheseFromDs(Synthese synthese, SyntheseDs syntheseDs) {
        synthese.setCreatedDate(syntheseDs.getCreatedDate());
        synthese.setObservation(syntheseDs.getObservation());
        return synthese;
    }
}
