package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.dossiermedical.entity.Antecedent;
import com.meddjamm.sn.dossiermedical.remote.model.AntecedentDs;
import com.meddjamm.sn.dossiermedical.repository.AntecedentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AntecedentAssembler {

    private final AntecedentRepository antecedentRepository;

    public AntecedentAssembler(AntecedentRepository antecedentRepository) {
        this.antecedentRepository = antecedentRepository;
    }

    public List<AntecedentDs> assembleEntitiesFrom(List<Antecedent> antecedents) {
        return antecedents.stream().map(this::assembleEntityToDs).toList();
    }

    public AntecedentDs assembleEntityToDs(Antecedent antecedent) {
        AntecedentDs antecedentDs = new AntecedentDs();
        if (antecedent.getId() != null)
            antecedentDs.setId(antecedent.getId());
//        antecedentDs.setAntecedentsMedicaux(new ArrayList<>(antecedent.getAntecedentsMedicaux()));
//        antecedentDs.setAntecedentsChirurgicaux(new ArrayList<>(antecedent.getAntecedentsChirurgicaux()));
//        antecedentDs.setAntecedentsGynecologiques(new ArrayList<>(antecedent.getAntecedentsGynecologiques()));
//        antecedentDs.setAntecedentsFamilialsAscendant(new ArrayList<>(antecedent.getAntecedentsFamilialsAscendant()));
//        antecedentDs.setAntecedentsFamilialsCollateral(new ArrayList<>(antecedent.getAntecedentsFamilialsCollateral()));
//        antecedentDs.setAntecedentsFamilialsDescendant(new ArrayList<>(antecedent.getAntecedentsFamilialsDescendant()));
//        antecedentDs.setModeVies(new ArrayList<>(antecedent.getModeVies()));
        antecedentDs.setActif(antecedent.isActif());
        antecedentDs.setCreateDate(antecedent.getCreateDate());
        antecedentDs.setAntecedentsMedicaux(antecedent.getAntecedentsMedicaux());
        antecedentDs.setAntecedentsChirurgicaux(antecedent.getAntecedentsChirurgicaux());
        antecedentDs.setAntecedentsGynecologiques(antecedent.getAntecedentsGynecologiques());
        antecedentDs.setAntecedentsFamilialsAscendant(antecedent.getAntecedentsFamilialsAscendant());
        antecedentDs.setAntecedentsFamilialsCollateral(antecedent.getAntecedentsFamilialsCollateral());
        antecedentDs.setAntecedentsFamilialsDescendant(antecedent.getAntecedentsFamilialsDescendant());
        antecedentDs.setModeVies(antecedent.getModeVies());
        return antecedentDs;
    }

    public Antecedent assembleAntecedentFromDs(AntecedentDs antecedentDs) {
        Antecedent antecedent = new Antecedent();
        if (antecedentDs.getId() != null)
            antecedent.setId(antecedentDs.getId());
        antecedent.setCreateDate(antecedentDs.getCreateDate());
        antecedent.setActif(antecedentDs.isActif());
        antecedent.setAntecedentsMedicaux(antecedentDs.getAntecedentsMedicaux());
        antecedent.setAntecedentsChirurgicaux(antecedentDs.getAntecedentsChirurgicaux());
        antecedent.setAntecedentsGynecologiques(antecedentDs.getAntecedentsGynecologiques());
        antecedent.setAntecedentsFamilialsAscendant(antecedentDs.getAntecedentsFamilialsAscendant());
        antecedent.setAntecedentsFamilialsCollateral(antecedentDs.getAntecedentsFamilialsCollateral());
        antecedent.setAntecedentsFamilialsDescendant(antecedentDs.getAntecedentsFamilialsDescendant());
        antecedent.setModeVies(antecedentDs.getModeVies());
        return antecedent;
    }

    public Antecedent assembleUpdateAntecedentFromDs(Antecedent antecedent, AntecedentDs antecedentDs) {
        antecedent.setAntecedentsMedicaux(antecedentDs.getAntecedentsMedicaux());
        antecedent.setAntecedentsChirurgicaux(antecedentDs.getAntecedentsChirurgicaux());
        antecedent.setAntecedentsGynecologiques(antecedentDs.getAntecedentsGynecologiques());
        antecedent.setAntecedentsFamilialsAscendant(antecedentDs.getAntecedentsFamilialsAscendant());
        antecedent.setAntecedentsFamilialsCollateral(antecedentDs.getAntecedentsFamilialsCollateral());
        antecedent.setAntecedentsFamilialsDescendant(antecedentDs.getAntecedentsFamilialsDescendant());
        antecedent.setModeVies(antecedentDs.getModeVies());
        return antecedent;
    }
}
