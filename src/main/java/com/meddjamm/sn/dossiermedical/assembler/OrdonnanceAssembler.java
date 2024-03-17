package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.Ordonnance;
import com.meddjamm.sn.dossiermedical.remote.model.OrdonnanceDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdonnanceAssembler {

    private final OrdonnanceItemAssembler ordonnanceItemAssembler;
    private final UtilisateurService utilisateurService;

    public OrdonnanceAssembler(OrdonnanceItemAssembler ordonnanceItemAssembler,
                               UtilisateurService utilisateurService) {
        this.ordonnanceItemAssembler = ordonnanceItemAssembler;
        this.utilisateurService = utilisateurService;
    }

    public List<OrdonnanceDs> assembleEntitiesFrom(List<Ordonnance> Ordonnances) {
        return Ordonnances.stream().map(this::assembleEntityToDs).toList();
    }

    public List<Ordonnance> assembleEntitiesFromDs(List<OrdonnanceDs> OrdonnanceDs) {
        return OrdonnanceDs.stream().map(this::assembleOrdonnanceFromDs).toList();
    }

    public OrdonnanceDs assembleEntityToDs(Ordonnance Ordonnance) {
        OrdonnanceDs OrdonnanceDs = new OrdonnanceDs();
        OrdonnanceDs.setId(Ordonnance.getId());
        OrdonnanceDs.setOrdonnanceItemDs(ordonnanceItemAssembler.createListOrdonnanceItemDs(Ordonnance.getOrdonnanceItems()));
        OrdonnanceDs.setCreatedDate(Ordonnance.getCreatedDate());
        OrdonnanceDs.setActif(Ordonnance.isActif());
        OrdonnanceDs.setCreatedBy(Ordonnance.getCreatedBy());
        OrdonnanceDs.setCircuitPatientId(Ordonnance.getCircuitPatientId());
        OrdonnanceDs.setCreatedBy(Ordonnance.getCreatedBy());
        if (Ordonnance.getCreatedBy() != null) {
            Utilisateur utilisateur = utilisateurService.findUserById(Ordonnance.getCreatedBy());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            OrdonnanceDs.setNomCompletAgent(nomAgent);
        }
        return OrdonnanceDs;
    }

    public Ordonnance assembleOrdonnanceFromDs(OrdonnanceDs OrdonnanceDs) {
        Ordonnance Ordonnance = new Ordonnance();
        Ordonnance.setId(OrdonnanceDs.getId());
        Ordonnance.setOrdonnanceItems(ordonnanceItemAssembler.createSetOrdonnanceItem(OrdonnanceDs.getOrdonnanceItemDs()));
        Ordonnance.setCreatedDate(OrdonnanceDs.getCreatedDate());
        Ordonnance.setActif(OrdonnanceDs.isActif());
        Ordonnance.setCreatedBy(OrdonnanceDs.getCreatedBy());
        Ordonnance.setCircuitPatientId(OrdonnanceDs.getCircuitPatientId());
        return Ordonnance;
    }
}
