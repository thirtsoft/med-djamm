package com.meddjamm.sn.rh.assembler;

import com.meddjamm.sn.rh.entity.Specialite;
import com.meddjamm.sn.rh.remote.model.SpecialiteDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpecialiteAssembler {

    public List<SpecialiteDs> assembleEntitiesFrom(List<Specialite> specialites) {
        return specialites.stream().map(this::assembleSpecialiteFrom).toList();
    }

    public Specialite assembleSpecialiteFromDs(SpecialiteDs specialiteDs) {
        Specialite specialite = new Specialite();
        specialite.setId(specialiteDs.getId());
        specialite.setDesignation(specialiteDs.getDesignation());
        specialite.setDateCreated(specialiteDs.getDateCreated());
        specialite.setActif(specialiteDs.isActif());
        return specialite;
    }

    public SpecialiteDs assembleSpecialiteFrom(Specialite specialite) {
        SpecialiteDs specialiteDs = new SpecialiteDs();
        specialiteDs.setId(specialite.getId());
        specialiteDs.setDesignation(specialite.getDesignation());
        specialiteDs.setDateCreated(specialite.getDateCreated());
        specialiteDs.setActif(specialite.isActif());
        return specialiteDs;
    }
}
