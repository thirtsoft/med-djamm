package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.TraitementMedical;
import com.meddjamm.sn.dossiermedical.remote.model.TraitementMedicalDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TraitementMedicalAssembler {

    private final OrdonnanceItemAssembler ordonnanceItemAssembler;

    private final TraitementMedicalItemAssembler traitementMedicalItemAssembler;

    private final UtilisateurService utilisateurService;

    public TraitementMedicalAssembler(OrdonnanceItemAssembler ordonnanceItemAssembler,
                                      TraitementMedicalItemAssembler traitementMedicalItemAssembler,
                                      UtilisateurService utilisateurService) {
        this.ordonnanceItemAssembler = ordonnanceItemAssembler;
        this.traitementMedicalItemAssembler = traitementMedicalItemAssembler;
        this.utilisateurService = utilisateurService;
    }

    public List<TraitementMedicalDs> assembleEntitiesFrom(List<TraitementMedical> traitementMedicals) {
        return traitementMedicals.stream().map(this::assembleEntityToDs).toList();
    }

    public List<TraitementMedical> assembleEntitiesFromDs(List<TraitementMedicalDs> traitementMedicalDs) {
        return traitementMedicalDs.stream().map(this::assembleTraitementMedicalFromDs).toList();
    }

    public TraitementMedicalDs assembleEntityToDs(TraitementMedical traitementMedical) {
        TraitementMedicalDs traitementMedicalDs = new TraitementMedicalDs();
        traitementMedicalDs.setId(traitementMedical.getId());
        traitementMedicalDs.setTraitementMedicalItemDs(traitementMedicalItemAssembler.createListTraitementMedicalItemDs(traitementMedical.getTraitementMedicalItems()));
        traitementMedicalDs.setCreatedDate(traitementMedical.getCreatedDate());
        traitementMedicalDs.setActif(traitementMedical.isActif());
        traitementMedicalDs.setCreatedBy(traitementMedical.getCreatedBy());
        traitementMedicalDs.setCircuitPatientId(traitementMedical.getCircuitPatientId());
        traitementMedicalDs.setProtocole(traitementMedical.getProtocole());
        traitementMedicalDs.setProtocoleFileName(traitementMedical.getProtocoleFileName());
        traitementMedicalDs.setCircuitPatientId(traitementMedical.getCircuitPatientId());
        traitementMedicalDs.setCreatedBy(traitementMedical.getCreatedBy());
        if (traitementMedical.getCreatedBy() != null) {
            Utilisateur utilisateur = utilisateurService.findUserById(traitementMedical.getCreatedBy());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            traitementMedicalDs.setNomCompletAgent(nomAgent);
        }
        return traitementMedicalDs;
    }

    public TraitementMedical assembleTraitementMedicalFromDs(TraitementMedicalDs traitementMedicalDs) {
        TraitementMedical traitementMedical = new TraitementMedical();
        traitementMedical.setId(traitementMedicalDs.getId());
        traitementMedical.setTraitementMedicalItems(traitementMedicalItemAssembler.createSetTraitementMedicalItem(traitementMedicalDs.getTraitementMedicalItemDs()));
        traitementMedical.setCreatedDate(traitementMedicalDs.getCreatedDate());
        traitementMedical.setActif(traitementMedicalDs.isActif());
        traitementMedical.setCreatedBy(traitementMedicalDs.getCreatedBy());
        traitementMedical.setCircuitPatientId(traitementMedicalDs.getCircuitPatientId());
        traitementMedical.setProtocole(traitementMedicalDs.getProtocole());
        traitementMedical.setProtocoleFileName(traitementMedicalDs.getProtocoleFileName());
        traitementMedical.setCircuitPatientId(traitementMedicalDs.getCircuitPatientId());
        return traitementMedical;
    }
}
