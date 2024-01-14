package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.AntecedentFamilial;
import com.meddjamm.sn.remote.model.AntecedentFamilialDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AntecedentFamilialAssembler {

    public List<AntecedentFamilialDs> assembleEntitiesFrom(List<AntecedentFamilial> antecedentFamilials) {
        return antecedentFamilials.stream().map(this::assembleEntityToDs).toList();
    }

    public AntecedentFamilialDs assembleEntityToDs(AntecedentFamilial antecedentFamilial) {
        AntecedentFamilialDs antecedentFamilialDs = new AntecedentFamilialDs();
        antecedentFamilialDs.setId(antecedentFamilial.getId());
        antecedentFamilialDs.setIndexPatient(antecedentFamilial.getIndexPatient());
        antecedentFamilialDs.setAntecedentAscendant(antecedentFamilial.getAntecedentAscendant());
        antecedentFamilialDs.setAntecedentCollateral(antecedentFamilial.getAntecedentCollateral());
        antecedentFamilialDs.setAntecedentDescendant(antecedentFamilial.getAntecedentDescendant());
        antecedentFamilialDs.setCreatedDate(antecedentFamilial.getCreatedDate());
        antecedentFamilialDs.setActif(antecedentFamilial.isActif());
        return antecedentFamilialDs;
    }

    public AntecedentFamilial assembleAntecedentFamilialFromDs(AntecedentFamilialDs antecedentFamilialDs) {
        AntecedentFamilial antecedentFamilial = new AntecedentFamilial();
        antecedentFamilial.setId(antecedentFamilialDs.getId());
        antecedentFamilial.setIndexPatient(antecedentFamilialDs.getIndexPatient());
        antecedentFamilial.setAntecedentAscendant(antecedentFamilialDs.getAntecedentAscendant());
        antecedentFamilial.setAntecedentCollateral(antecedentFamilialDs.getAntecedentCollateral());
        antecedentFamilial.setAntecedentDescendant(antecedentFamilialDs.getAntecedentDescendant());
        antecedentFamilial.setCreatedDate(antecedentFamilialDs.getCreatedDate());
        antecedentFamilial.setActif(antecedentFamilialDs.isActif());
        return antecedentFamilial;
    }
}
