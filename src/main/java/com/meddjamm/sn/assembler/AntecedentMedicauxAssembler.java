package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.AntecedentMedicaux;
import com.meddjamm.sn.remote.model.AntecedentMedicauxDs;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class AntecedentMedicauxAssembler {

    public List<AntecedentMedicauxDs> assembleEntitiesFrom(List<AntecedentMedicaux> antecedentMedicauxes) {
        return antecedentMedicauxes.stream().map(this::assembleEntityToDs).toList();
    }

    public AntecedentMedicauxDs assembleEntityToDs(AntecedentMedicaux antecedentMedicaux) {
        AntecedentMedicauxDs antecedentMedicauxDs = new AntecedentMedicauxDs();
        antecedentMedicauxDs.setId(antecedentMedicaux.getId());
        antecedentMedicauxDs.setIndexPatient(antecedentMedicaux.getIndexPatient());
        antecedentMedicauxDs.setMaladiesAntecedents(new ArrayList<>(antecedentMedicaux.getMaladiesAntecedent()));
        antecedentMedicauxDs.setCreateDate(antecedentMedicaux.getCreateDate());
        antecedentMedicauxDs.setActif(antecedentMedicaux.isActif());
        return antecedentMedicauxDs;
    }

    public AntecedentMedicaux assembleAntecedentMedicauxFromDs(AntecedentMedicauxDs antecedentMedicauxDs) {
        AntecedentMedicaux antecedentMedicaux = new AntecedentMedicaux();
        antecedentMedicaux.setId(antecedentMedicauxDs.getId());
        antecedentMedicaux.setIndexPatient(antecedentMedicauxDs.getIndexPatient());
        antecedentMedicaux.setMaladiesAntecedent(new HashSet<>(antecedentMedicauxDs.getMaladiesAntecedents()));
        antecedentMedicaux.setCreateDate(antecedentMedicauxDs.getCreateDate());
        antecedentMedicaux.setActif(antecedentMedicauxDs.isActif());
        return antecedentMedicaux;
    }
}
