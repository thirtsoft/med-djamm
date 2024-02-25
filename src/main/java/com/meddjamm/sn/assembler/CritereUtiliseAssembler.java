package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.CritereUtilise;
import com.meddjamm.sn.remote.model.CritereUtiliseDs;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CritereUtiliseAssembler {

    public List<CritereUtiliseDs> assembleEntitiesFrom(List<CritereUtilise> critereUtilises) {
        return critereUtilises.stream().map(this::assembleEntityToDs).toList();
    }

    public List<CritereUtiliseDs> createListCritereUtiliseDs(Set<CritereUtilise> critereUtilises) {
        if (critereUtilises == null)
            return Collections.emptyList();
        List<CritereUtiliseDs> dtos = new ArrayList<>();
        for (CritereUtilise critereUtilise : critereUtilises) {
            dtos.add(assembleEntityToDs(critereUtilise));
        }
        return dtos;
    }

    public Set<CritereUtilise> createSetCritereUtilise(List<CritereUtiliseDs> critereUtiliseDs) {
        if (critereUtiliseDs == null)
            return null;
        Set<CritereUtilise> actions = new HashSet<>();
        for (CritereUtiliseDs dto : critereUtiliseDs)
            if (dto != null)
                actions.add(assembleCritereUtiliseFromDs(dto));
        return actions;
    }

    public CritereUtiliseDs assembleEntityToDs(CritereUtilise critereUtilise) {
        CritereUtiliseDs critereUtiliseDs = new CritereUtiliseDs();
        critereUtiliseDs.setId(critereUtilise.getId());
        critereUtiliseDs.setLibelle(critereUtilise.getLibelle());
        return critereUtiliseDs;
    }

    public CritereUtilise assembleCritereUtiliseFromDs(CritereUtiliseDs critereUtiliseDs) {
        CritereUtilise critereUtilise = new CritereUtilise();
        critereUtilise.setId(critereUtiliseDs.getId());
        critereUtilise.setLibelle(critereUtiliseDs.getLibelle());
        return critereUtilise;
    }
}
