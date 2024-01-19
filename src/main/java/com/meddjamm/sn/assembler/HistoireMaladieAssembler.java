package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.HistoireMaladie;
import com.meddjamm.sn.remote.model.HistoireMaladieDs;
import com.meddjamm.sn.remote.model.MaladieDs;
import com.meddjamm.sn.services.MaladieService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class HistoireMaladieAssembler {

    private final MaladieService maladieService;

    private final MaladieAssembler maladieAssembler;

    public HistoireMaladieAssembler(MaladieService maladieService,
                                    MaladieAssembler maladieAssembler) {
        this.maladieService = maladieService;
        this.maladieAssembler = maladieAssembler;
    }

    public List<HistoireMaladieDs> assembleEntitiesFrom(List<HistoireMaladie> histoireMaladies) {
        return histoireMaladies.stream().map(this::assembleEntityToDs).toList();
    }

    public HistoireMaladieDs assembleEntityToDs(HistoireMaladie histoireMaladie) {
        HistoireMaladieDs histoireMaladieDs = new HistoireMaladieDs();
        histoireMaladieDs.setId(histoireMaladie.getId());
      //  histoireMaladieDs.setIndexPatient(histoireMaladie.getIndexPatient());
        histoireMaladieDs.setAge(histoireMaladie.getAge());
        histoireMaladieDs.setCirconstance(histoireMaladie.getCirconstance());
        histoireMaladieDs.setInfoComplementaire(histoireMaladie.getInfoComplementaire());
        histoireMaladieDs.setDateDiagnostic(histoireMaladie.getDateDiagnostic());
        histoireMaladieDs.setCreatedDate(histoireMaladie.getCreatedDate());
        histoireMaladieDs.setActif(histoireMaladie.isActif());
        histoireMaladieDs.setAntecedentPrecedents(createListeMaladieDs(histoireMaladie.getAntecedentPrecedents()));
        histoireMaladieDs.setAntecedentActuels(createListeMaladieDs(histoireMaladie.getAntecedentActuels()));
        return histoireMaladieDs;
    }

    public List<MaladieDs> createListeMaladieDs(Set<Long> maladies) {
        List<MaladieDs> dtos = new ArrayList<>();
        for (Long id : maladies)
            dtos.add(maladieAssembler.assembleEntityToDs(maladieService.findById(id)));
        //   dtos.add(agentService.findAgentById(id));
        return dtos;
    }

    public Set<Long> createSetMaladie(List<MaladieDs> dtos) {
        if (dtos == null)
            return null;
        Set<Long> agents = new HashSet<>();
        for (MaladieDs dto : dtos)
            if (dto != null)
                agents.add(dto.getId());
        return agents;
    }

    public HistoireMaladie assembleHistoireMaladieFromDs(HistoireMaladieDs histoireMaladieDs) {
        HistoireMaladie histoireMaladie = new HistoireMaladie();
        histoireMaladieDs.setId(histoireMaladie.getId());
    //    histoireMaladie.setIndexPatient(histoireMaladieDs.getIndexPatient());
        histoireMaladie.setAge(histoireMaladieDs.getAge());
        histoireMaladie.setCirconstance(histoireMaladieDs.getCirconstance());
        histoireMaladie.setInfoComplementaire(histoireMaladieDs.getInfoComplementaire());
        histoireMaladie.setDateDiagnostic(histoireMaladieDs.getDateDiagnostic());
        histoireMaladie.setCreatedDate(histoireMaladieDs.getCreatedDate());
        histoireMaladie.setActif(histoireMaladieDs.isActif());
        histoireMaladie.setAntecedentPrecedents(createSetMaladie(histoireMaladieDs.getAntecedentPrecedents()));
        histoireMaladie.setAntecedentActuels(createSetMaladie(histoireMaladieDs.getAntecedentActuels()));
        return histoireMaladie;
    }
}
