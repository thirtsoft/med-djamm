package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.Ordonnance;
import com.meddjamm.sn.dossiermedical.remote.model.AllCircuitPatientDs;
import com.meddjamm.sn.dossiermedical.remote.model.OrdonnanceDs;
import com.meddjamm.sn.utils.UtilString;
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

    public List<AllCircuitPatientDs> assembleAllCircuitPatientEntitiesFrom(List<Ordonnance> ordonnances) {
        return ordonnances.stream().map(this::assembleAllCircuitPatientDsFromEntity).toList();
    }

    public AllCircuitPatientDs assembleAllCircuitPatientDsFromEntity(Ordonnance ordonnance) {
        AllCircuitPatientDs allCircuitPatientDs = new AllCircuitPatientDs();
        if (ordonnance.getId() != null)
            allCircuitPatientDs.setId(ordonnance.getId());
        allCircuitPatientDs.setActif(ordonnance.isActif());
        allCircuitPatientDs.setNumeroCircuit(UtilString.createNumeroCircuitPatient(
                ordonnance.getCircuitPatient().getNumeroCircuit()));
        allCircuitPatientDs.setCreateDate(ordonnance.getCreatedDate());
        allCircuitPatientDs.setType("Ordonnance");
        allCircuitPatientDs.setCode(ordonnance.getCircuitPatient().getMatricule());
        if (ordonnance.getCreatedByUser() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(ordonnance.getCreatedByUser());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            allCircuitPatientDs.setNomCompletAgent(nomAgent);
        }
        return allCircuitPatientDs;
    }
}
