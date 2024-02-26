package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.dossiermedical.entity.TraitementMedical;
import com.meddjamm.sn.dossiermedical.remote.model.TraitementMedicalDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TraitementMedicalAssembler {

    private final OrdonnanceItemAssembler ordonnanceItemAssembler;

    public TraitementMedicalAssembler(OrdonnanceItemAssembler ordonnanceItemAssembler) {
        this.ordonnanceItemAssembler = ordonnanceItemAssembler;
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
        traitementMedicalDs.setOrdonnanceItemDs(ordonnanceItemAssembler.createListOrdonnanceItemDs(traitementMedical.getOrdonnanceItems()));
        traitementMedicalDs.setCreatedDate(traitementMedical.getCreatedDate());
        traitementMedicalDs.setActif(traitementMedical.isActif());
        traitementMedicalDs.setCreatedBy(traitementMedical.getCreatedBy());
        traitementMedicalDs.setCircuitPatientId(traitementMedical.getCircuitPatientId());
        traitementMedicalDs.setProtocole(traitementMedical.getProtocole());
        traitementMedicalDs.setProtocoleFileName(traitementMedical.getProtocoleFileName());
        traitementMedicalDs.setCircuitPatientId(traitementMedical.getCircuitPatientId());
        traitementMedicalDs.setCreatedBy(traitementMedical.getCreatedBy());
        return traitementMedicalDs;
    }

    public TraitementMedical assembleTraitementMedicalFromDs(TraitementMedicalDs traitementMedicalDs) {
        TraitementMedical traitementMedical = new TraitementMedical();
        traitementMedical.setId(traitementMedicalDs.getId());
        traitementMedical.setOrdonnanceItems(ordonnanceItemAssembler.createSetOrdonnanceItem(traitementMedicalDs.getOrdonnanceItemDs()));
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
