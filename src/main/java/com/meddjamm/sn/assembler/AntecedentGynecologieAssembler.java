package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.AntecedentGynecologie;
import com.meddjamm.sn.remote.model.AntecedentGynecologieDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AntecedentGynecologieAssembler {

    public List<AntecedentGynecologieDs> assembleEntitiesFrom(List<AntecedentGynecologie> antecedentGynecologies) {
        return antecedentGynecologies.stream().map(this::assembleEntityToDs).toList();
    }

    public AntecedentGynecologieDs assembleEntityToDs(AntecedentGynecologie antecedentGynecologie) {
        AntecedentGynecologieDs antecedentGynecologieDs = new AntecedentGynecologieDs();
        antecedentGynecologieDs.setId(antecedentGynecologie.getId());
        antecedentGynecologieDs.setLibelle(antecedentGynecologie.getLibelle());
        antecedentGynecologieDs.setIndexPatient(antecedentGynecologie.getIndexPatient());
        antecedentGynecologieDs.setCreatedDate(antecedentGynecologie.getCreatedDate());
        antecedentGynecologieDs.setActif(antecedentGynecologie.isActif());
        return antecedentGynecologieDs;
    }

    public AntecedentGynecologie assembleAntecedentGynecologieFromDs(AntecedentGynecologieDs antecedentGynecologieDs) {
        AntecedentGynecologie antecedentGynecologie = new AntecedentGynecologie();
        antecedentGynecologie.setId(antecedentGynecologieDs.getId());
        antecedentGynecologie.setLibelle(antecedentGynecologieDs.getLibelle());
        antecedentGynecologie.setIndexPatient(antecedentGynecologieDs.getIndexPatient());
        antecedentGynecologie.setCreatedDate(antecedentGynecologieDs.getCreatedDate());
        antecedentGynecologie.setActif(antecedentGynecologieDs.isActif());
        return antecedentGynecologie;
    }
}
