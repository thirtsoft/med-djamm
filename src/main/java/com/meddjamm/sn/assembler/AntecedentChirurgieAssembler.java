package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.AntecedentChirurgie;
import com.meddjamm.sn.remote.model.AntecedentChirurgieDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AntecedentChirurgieAssembler {

    public List<AntecedentChirurgieDs> assembleEntitiesFrom(List<AntecedentChirurgie> antecedentChirurgies) {
        return antecedentChirurgies.stream().map(this::assembleEntityToDs).toList();
    }

    public AntecedentChirurgieDs assembleEntityToDs(AntecedentChirurgie antecedentChirurgie) {
        AntecedentChirurgieDs antecedentChirurgieDs = new AntecedentChirurgieDs();
        antecedentChirurgieDs.setId(antecedentChirurgie.getId());
        antecedentChirurgieDs.setLibelle(antecedentChirurgie.getLibelle());
        antecedentChirurgieDs.setIndexPatient(antecedentChirurgie.getIndexPatient());
        antecedentChirurgieDs.setCreatedDate(antecedentChirurgie.getCreatedDate());
        antecedentChirurgieDs.setActif(antecedentChirurgie.isActif());
        return antecedentChirurgieDs;
    }

    public AntecedentChirurgie assembleAntecedentChirurgieFromDs(AntecedentChirurgieDs antecedentChirurgieDs) {
        AntecedentChirurgie antecedentChirurgie = new AntecedentChirurgie();
        antecedentChirurgie.setId(antecedentChirurgieDs.getId());
        antecedentChirurgie.setLibelle(antecedentChirurgieDs.getLibelle());
        antecedentChirurgie.setIndexPatient(antecedentChirurgieDs.getIndexPatient());
        antecedentChirurgie.setCreatedDate(antecedentChirurgieDs.getCreatedDate());
        antecedentChirurgie.setActif(antecedentChirurgieDs.isActif());
        return antecedentChirurgie;
    }
}
