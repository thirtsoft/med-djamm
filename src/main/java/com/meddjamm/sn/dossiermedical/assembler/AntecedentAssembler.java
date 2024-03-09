package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.dossiermedical.entity.Antecedent;
import com.meddjamm.sn.dossiermedical.remote.model.AntecedentDs;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class AntecedentAssembler {

    public List<AntecedentDs> assembleEntitiesFrom(List<Antecedent> antecedents) {
        return antecedents.stream().map(this::assembleEntityToDs).toList();
    }

    public AntecedentDs assembleEntityToDs(Antecedent antecedent) {
        AntecedentDs antecedentDs = new AntecedentDs();
        antecedentDs.setId(antecedent.getId());
        antecedentDs.setAntecedentsMedicaux(new ArrayList<>(antecedent.getAntecedentsMedicaux()));
        antecedentDs.setAntecedentsChirurgicaux(new ArrayList<>(antecedent.getAntecedentsChirurgicaux()));
        antecedentDs.setAntecedentsGynecologiques(new ArrayList<>(antecedent.getAntecedentsGynecologiques()));
        antecedentDs.setAntecedentsFamilialsAscendant(new ArrayList<>(antecedent.getAntecedentsFamilialsAscendant()));
        antecedentDs.setAntecedentsFamilialsCollateral(new ArrayList<>(antecedent.getAntecedentsFamilialsCollateral()));
        antecedentDs.setAntecedentsFamilialsDescendant(new ArrayList<>(antecedent.getAntecedentsFamilialsDescendant()));
        antecedentDs.setModeVies(new ArrayList<>(antecedent.getModeVies()));
        antecedentDs.setActif(antecedent.isActif());
        antecedentDs.setCreateDate(antecedent.getCreateDate());
        return antecedentDs;
    }

    public Antecedent assembleAntecedentFromDs(AntecedentDs antecedentDs) {
        Antecedent antecedent = new Antecedent();
        antecedent.setId(antecedentDs.getId());
        antecedent.setCreateDate(antecedentDs.getCreateDate());
        antecedent.setActif(antecedentDs.isActif());
        antecedent.setAntecedentsMedicaux(new HashSet<>(antecedentDs.getAntecedentsMedicaux()));
        antecedent.setAntecedentsChirurgicaux(new HashSet<>(antecedentDs.getAntecedentsChirurgicaux()));
        antecedent.setAntecedentsGynecologiques(new HashSet<>(antecedentDs.getAntecedentsGynecologiques()));
        antecedent.setAntecedentsFamilialsAscendant(new HashSet<>(antecedentDs.getAntecedentsFamilialsAscendant()));
        antecedent.setAntecedentsFamilialsCollateral(new HashSet<>(antecedentDs.getAntecedentsFamilialsCollateral()));
        antecedent.setAntecedentsFamilialsDescendant(new HashSet<>(antecedentDs.getAntecedentsFamilialsDescendant()));
        antecedent.setModeVies(new HashSet<>(antecedentDs.getModeVies()));
        return antecedent;
    }
}
