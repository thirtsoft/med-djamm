package com.meddjamm.sn.dossiermedical.remote.controller;


import com.meddjamm.sn.dossiermedical.assembler.ExamenComplementaireAssembler;
import com.meddjamm.sn.dossiermedical.entity.ExamenComplementaire;
import com.meddjamm.sn.dossiermedical.remote.controller.api.ExamenComplementaireApi;
import com.meddjamm.sn.dossiermedical.remote.model.ExamenComplementaireDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.ExamenComplementaireDs;
import com.meddjamm.sn.dossiermedical.services.ExamenComplementaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExamenComplementaireController implements ExamenComplementaireApi {

    private final ExamenComplementaireService examenComplementaireService;

    private final ExamenComplementaireAssembler examenComplementaireAssembler;

    public ExamenComplementaireController(ExamenComplementaireService examenComplementaireService,
                                          ExamenComplementaireAssembler examenComplementaireAssembler) {
        this.examenComplementaireService = examenComplementaireService;
        this.examenComplementaireAssembler = examenComplementaireAssembler;
    }

    @Override
    public ResponseEntity<ExamenComplementaireDetailDs> creerExamenComplementaire(ExamenComplementaireDs examenComplementaireDs) {
        ExamenComplementaire examenComplementaire = examenComplementaireAssembler.assembleExamenComplementaireFromDs(examenComplementaireDs);
        return new ResponseEntity<>(examenComplementaireAssembler
                .assembleEntitiesToDs(examenComplementaireService.saveExamenComplementaire(examenComplementaire)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ExamenComplementaireDetailDs> updateExamenComplementaire(Long id, ExamenComplementaireDs examenComplementaireDs) throws Exception {
        ExamenComplementaire examenComplementaire = examenComplementaireAssembler.assembleExamenComplementaireFromDs(examenComplementaireDs);
        return new ResponseEntity<>(examenComplementaireAssembler
                .assembleEntitiesToDs(examenComplementaireService.updateExamenComplementaire(id, examenComplementaire)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ExamenComplementaireDetailDs> findById(Long id) {
        return new ResponseEntity<>(examenComplementaireAssembler.assembleEntitiesToDs(
                examenComplementaireService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ExamenComplementaireDetailDs>> findAllExamenComplementaires() {
        return new ResponseEntity<>(examenComplementaireAssembler.assembleEntitiesFrom(
                examenComplementaireService.findAllExamenComplementaires()
        ), HttpStatus.OK);
    }

    @Override
    public void deleteExamenComplementaire(Long id) {
        examenComplementaireService.deleteExamenComplementaire(id);
    }

    @Override
    public ResponseEntity<List<ExamenComplementaireDetailDs>> findAllExamenComplementairesByPatientId(String code) {
        return new ResponseEntity<>(examenComplementaireAssembler.assembleEntitiesFrom(
                examenComplementaireService.findExamenComplementaireByPatientId(code)
        ), HttpStatus.OK);
    }
}
