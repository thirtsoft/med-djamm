package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.rh.assembler.MedicamentAssembler;
import com.meddjamm.sn.dossiermedical.entity.OrdonnanceItem;
import com.meddjamm.sn.rh.remote.model.MedicamentDs;
import com.meddjamm.sn.dossiermedical.remote.model.OrdonnanceItemDs;
import com.meddjamm.sn.rh.services.MedicamentService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrdonnanceItemAssembler {

    private final MedicamentService medicamentService;

    private final MedicamentAssembler medicamentAssembler;

    public OrdonnanceItemAssembler(MedicamentService medicamentService,
                                   MedicamentAssembler medicamentAssembler) {
        this.medicamentService = medicamentService;
        this.medicamentAssembler = medicamentAssembler;
    }

    public List<OrdonnanceItemDs> assembleEntitiesFrom(List<OrdonnanceItem> ordonnanceItems) {
        return ordonnanceItems.stream().map(this::assembleEntityToDs).toList();
    }

    public List<OrdonnanceItemDs> createListOrdonnanceItemDs(Set<OrdonnanceItem> ordonnanceItems) {
        if (ordonnanceItems == null)
            return Collections.emptyList();
        List<OrdonnanceItemDs> dtos = new ArrayList<>();
        for (OrdonnanceItem ordonnanceItem : ordonnanceItems) {
            dtos.add(assembleEntityToDs(ordonnanceItem));
        }
        return dtos;
    }

    public Set<OrdonnanceItem> createSetOrdonnanceItem(List<OrdonnanceItemDs> ordonnanceItemDs) {
        if (ordonnanceItemDs == null)
            return null;
        Set<OrdonnanceItem> actions = new HashSet<>();
        for (OrdonnanceItemDs dto : ordonnanceItemDs)
            if (dto != null)
                actions.add(assembleOrdonnanceItemFromDs(dto));
        return actions;
    }

    public OrdonnanceItemDs assembleEntityToDs(OrdonnanceItem ordonnanceItem) {
        OrdonnanceItemDs ordonnanceItemDs = new OrdonnanceItemDs();
        ordonnanceItemDs.setId(ordonnanceItem.getId());
        ordonnanceItemDs.setCode(ordonnanceItem.getCode());
        ordonnanceItemDs.setPsologie(ordonnanceItem.getPsologie());
        ordonnanceItemDs.setNbrePrise(ordonnanceItem.getNbrePrise());
        if (ordonnanceItem.getCode() != null) {
            MedicamentDs medicamentDs = medicamentAssembler.assembleEntityToDs(
                    medicamentService.findByCode(ordonnanceItem.getCode())
            );
            ordonnanceItemDs.setMedicamentDs(medicamentDs);
        }
        return ordonnanceItemDs;
    }

    public OrdonnanceItem assembleOrdonnanceItemFromDs(OrdonnanceItemDs ordonnanceItemDs) {
        OrdonnanceItem ordonnanceItem = new OrdonnanceItem();
        ordonnanceItem.setId(ordonnanceItemDs.getId());
        ordonnanceItem.setCode(ordonnanceItemDs.getCode());
        ordonnanceItem.setPsologie(ordonnanceItemDs.getPsologie());
        ordonnanceItem.setNbrePrise(ordonnanceItemDs.getNbrePrise());
        return ordonnanceItem;
    }
}