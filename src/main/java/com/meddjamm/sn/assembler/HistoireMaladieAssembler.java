package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.HistoireMaladie;
import com.meddjamm.sn.remote.model.HistoireMaladieDs;
import com.meddjamm.sn.rh.services.MaladieService;
import org.springframework.stereotype.Component;

import java.util.List;

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
        histoireMaladieDs.setAge(histoireMaladie.getAge());
        histoireMaladieDs.setCirconstance(histoireMaladie.getCirconstance());
        histoireMaladieDs.setInfoComplementaire(histoireMaladie.getInfoComplementaire());
        histoireMaladieDs.setDateDiagnostic(histoireMaladie.getDateDiagnostic());
        histoireMaladieDs.setCreatedDate(histoireMaladie.getCreatedDate());
        histoireMaladieDs.setActif(histoireMaladie.isActif());
        //  histoireMaladieDs.setAntecedentPrecedents(createListeMaladieDs(histoireMaladie.getAntecedentPrecedents()));
        //   histoireMaladieDs.setAntecedentActuels(createListeMaladieDs(histoireMaladie.getAntecedentActuels()));
        return histoireMaladieDs;
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
        return histoireMaladie;
    }
}
