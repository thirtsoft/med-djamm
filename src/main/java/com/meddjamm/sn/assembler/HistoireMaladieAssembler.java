package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.HistoireMaladie;
import com.meddjamm.sn.remote.model.AtteinteDs;
import com.meddjamm.sn.remote.model.HistoireMaladieDs;
import com.meddjamm.sn.remote.model.MaladieDs;
import com.meddjamm.sn.services.AtteinteService;
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

    private final AtteinteService atteinteService;

    private final AtteinteAssembler atteinteAssembler;

    public HistoireMaladieAssembler(MaladieService maladieService,
                                    MaladieAssembler maladieAssembler,
                                    AtteinteService atteinteService,
                                    AtteinteAssembler atteinteAssembler) {
        this.maladieService = maladieService;
        this.maladieAssembler = maladieAssembler;
        this.atteinteService = atteinteService;
        this.atteinteAssembler = atteinteAssembler;
    }

    public List<HistoireMaladieDs> assembleEntitiesFrom(List<HistoireMaladie> histoireMaladies) {
        return histoireMaladies.stream().map(this::assembleEntityToDs).toList();
    }

    public HistoireMaladieDs assembleEntityToDs(HistoireMaladie histoireMaladie) {
        HistoireMaladieDs histoireMaladieDs = new HistoireMaladieDs();
        histoireMaladieDs.setId(histoireMaladie.getId());
        histoireMaladieDs.setAge(histoireMaladie.getAge());
        histoireMaladieDs.setCirconstance(histoireMaladie.getCirconstance());
        histoireMaladieDs.setInfoComplementaire(histoireMaladie.getInfoComplementaire());
        histoireMaladieDs.setDateDiagnostic(histoireMaladie.getDateDiagnostic());
        histoireMaladieDs.setCreatedDate(histoireMaladie.getCreatedDate());
        histoireMaladieDs.setActif(histoireMaladie.isActif());
        histoireMaladieDs.setAtteintePrecedents(createListeAtteinteDs(histoireMaladie.getAtteintePrecedents()));
        histoireMaladieDs.setAtteinteActuels(createListeAtteinteDs(histoireMaladie.getAtteinteActuels()));
      //  histoireMaladieDs.setAntecedentPrecedents(createListeMaladieDs(histoireMaladie.getAntecedentPrecedents()));
     //   histoireMaladieDs.setAntecedentActuels(createListeMaladieDs(histoireMaladie.getAntecedentActuels()));
        return histoireMaladieDs;
    }

    public List<AtteinteDs> createListeAtteinteDs(Set<Long> atteintes) {
        List<AtteinteDs> dtos = new ArrayList<>();
        for (Long id : atteintes)
        //    dtos.add(maladieAssembler.assembleEntityToDs(maladieService.findById(id)));
            dtos.add(atteinteAssembler.assembleEntityToDs(atteinteService.findById(id)));
        //   dtos.add(agentService.findAgentById(id));
        return dtos;
    }

    public Set<Long> createSetAtteinte(List<AtteinteDs> dtos) {
        if (dtos == null)
            return null;
        Set<Long> atteintes = new HashSet<>();
        for (AtteinteDs dto : dtos)
            if (dto != null)
                atteintes.add(dto.getId());
        return atteintes;
    }

    public HistoireMaladie assembleHistoireMaladieFromDs(HistoireMaladieDs histoireMaladieDs) {
        HistoireMaladie histoireMaladie = new HistoireMaladie();
        histoireMaladieDs.setId(histoireMaladie.getId());
        histoireMaladie.setAge(histoireMaladieDs.getAge());
        histoireMaladie.setCirconstance(histoireMaladieDs.getCirconstance());
        histoireMaladie.setInfoComplementaire(histoireMaladieDs.getInfoComplementaire());
        histoireMaladie.setDateDiagnostic(histoireMaladieDs.getDateDiagnostic());
        histoireMaladie.setCreatedDate(histoireMaladieDs.getCreatedDate());
        histoireMaladie.setActif(histoireMaladieDs.isActif());
        histoireMaladie.setAtteintePrecedents(createSetAtteinte(histoireMaladieDs.getAtteintePrecedents()));
        histoireMaladie.setAtteinteActuels(createSetAtteinte(histoireMaladieDs.getAtteinteActuels()));
    //    histoireMaladie.setAntecedentPrecedents(createSetMaladie(histoireMaladieDs.getAntecedentPrecedents()));
    //    histoireMaladie.setAntecedentActuels(createSetMaladie(histoireMaladieDs.getAntecedentActuels()));
        return histoireMaladie;
    }
}
