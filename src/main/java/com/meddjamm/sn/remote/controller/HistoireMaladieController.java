package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.HistoireMaladieAssembler;
import com.meddjamm.sn.entity.HistoireMaladie;
import com.meddjamm.sn.remote.controller.api.HistoireMaladieApi;
import com.meddjamm.sn.remote.model.HistoireMaladieDs;
import com.meddjamm.sn.services.HistoireMaladieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class HistoireMaladieController implements HistoireMaladieApi {

    private final HistoireMaladieService histoireMaladieService;

    private final HistoireMaladieAssembler histoireMaladieAssembler;

    public HistoireMaladieController(HistoireMaladieService histoireMaladieService,
                                     HistoireMaladieAssembler histoireMaladieAssembler) {
        this.histoireMaladieService = histoireMaladieService;
        this.histoireMaladieAssembler = histoireMaladieAssembler;
    }

    @Override
    public void creerHistoireMaladie(HistoireMaladieDs histoireMaladieDs) {
        HistoireMaladie histoireMaladie = histoireMaladieAssembler.assembleHistoireMaladieFromDs(histoireMaladieDs);
        histoireMaladieService.saveHistoireMaladie(histoireMaladie);
    }

    @Override
    public void updateHistoireMaladie(Long id, HistoireMaladieDs histoireMaladieDs) throws Exception {
        HistoireMaladie histoireMaladie = histoireMaladieAssembler.assembleHistoireMaladieFromDs(histoireMaladieDs);
        histoireMaladieService.updateHistoireMaladie(id, histoireMaladie);
    }

    @Override
    public ResponseEntity<HistoireMaladieDs> findById(Long id) {
        return new ResponseEntity<>(histoireMaladieAssembler
                .assembleEntityToDs(histoireMaladieService.findById(id)), HttpStatus.OK);
    }

    @Override
    public void deleteHistoireMaladie(Long id) {
        histoireMaladieService.deleteHistoireMaladie(id);
    }
}
