package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.dossiermedical.entity.TraitementMedicalItem;
import com.meddjamm.sn.dossiermedical.remote.model.TraitementMedicalItemDs;
import com.meddjamm.sn.rh.assembler.MedicamentAssembler;
import com.meddjamm.sn.rh.remote.model.MedicamentDs;
import com.meddjamm.sn.rh.services.MedicamentService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TraitementMedicalItemAssembler {

    private final MedicamentService medicamentService;

    private final MedicamentAssembler medicamentAssembler;

    public TraitementMedicalItemAssembler(MedicamentService medicamentService, MedicamentAssembler medicamentAssembler) {
        this.medicamentService = medicamentService;
        this.medicamentAssembler = medicamentAssembler;
    }

    public List<TraitementMedicalItemDs> assembleEntitiesFrom(List<TraitementMedicalItem> traitementMedicalItemList) {
        return traitementMedicalItemList.stream().map(this::assembleEntityToDs).toList();
    }

    public List<TraitementMedicalItemDs> createListTraitementMedicalItemDs(Set<TraitementMedicalItem> traitementMedicalItems) {
        if (traitementMedicalItems == null) return Collections.emptyList();
        List<TraitementMedicalItemDs> dtos = new ArrayList<>();
        for (TraitementMedicalItem TraitementMedicalItem : traitementMedicalItems) {
            dtos.add(assembleEntityToDs(TraitementMedicalItem));
        }
        return dtos;
    }

    public Set<TraitementMedicalItem> createSetTraitementMedicalItem(List<TraitementMedicalItemDs> traitementMedicalItemDs) {
        if (traitementMedicalItemDs == null) return null;
        Set<TraitementMedicalItem> actions = new HashSet<>();
        for (TraitementMedicalItemDs dto : traitementMedicalItemDs)
            if (dto != null) actions.add(assembleTraitementMedicalItemFromDs(dto));
        return actions;
    }

    public TraitementMedicalItemDs assembleEntityToDs(TraitementMedicalItem traitementMedicalItem) {
        TraitementMedicalItemDs traitementMedicalItemDs = new TraitementMedicalItemDs();
        if (traitementMedicalItem.getId() != null)
            traitementMedicalItemDs.setId(traitementMedicalItem.getId());
        traitementMedicalItemDs.setPsologie(traitementMedicalItem.getPsologie());
        traitementMedicalItemDs.setNbrePrise(traitementMedicalItem.getNbrePrise());
        traitementMedicalItemDs.setAdministrePar(traitementMedicalItem.getAdministrePar());
        traitementMedicalItemDs.setEst_administre(traitementMedicalItem.getEst_administre());
        traitementMedicalItemDs.setMedicamendId(traitementMedicalItem.getMedicamendId());
        if (traitementMedicalItem.getMedicamendId() != null) {
            MedicamentDs medicamentDs = medicamentAssembler.assembleEntityToDs(medicamentService.findById(traitementMedicalItem.getMedicamendId()));
            traitementMedicalItemDs.setMedicamentDs(medicamentDs);
        }
        return traitementMedicalItemDs;
    }

    public TraitementMedicalItem assembleTraitementMedicalItemFromDs(TraitementMedicalItemDs traitementMedicalItemDs) {
        TraitementMedicalItem traitementMedicalItem = new TraitementMedicalItem();
        if (traitementMedicalItemDs.getId() != null)
            traitementMedicalItem.setId(traitementMedicalItemDs.getId());
        /*
        return actionListDs.stream()
                .map(actionDs -> actionService.findById(actionDs.getId()))
                .collect(Collectors.toSet());
        */
        if (traitementMedicalItemDs.getMedicamendId() != null)
            traitementMedicalItem.setMedicamendId(traitementMedicalItemDs.getMedicamendId());
        traitementMedicalItem.setPsologie(traitementMedicalItemDs.getPsologie());
        traitementMedicalItem.setNbrePrise(traitementMedicalItemDs.getNbrePrise());
        traitementMedicalItem.setAdministrePar(traitementMedicalItemDs.getAdministrePar());
        traitementMedicalItem.setEst_administre(traitementMedicalItemDs.getEst_administre());
        return traitementMedicalItem;
    }
}
