package com.meddjamm.sn.dossiermedical.remote.controller;

import com.meddjamm.sn.dossiermedical.assembler.ExamenBiologiqueAssembler;
import com.meddjamm.sn.dossiermedical.remote.controller.api.ExamenBiologiqueApi;
import com.meddjamm.sn.dossiermedical.remote.model.ExamenBiologiqueDs;
import com.meddjamm.sn.dossiermedical.services.ExamenBiologiqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExamenBiologiqueController implements ExamenBiologiqueApi {

    private final ExamenBiologiqueService examenBiologiqueService;

    private final ExamenBiologiqueAssembler examenBiologiqueAssembler;

    public ExamenBiologiqueController(ExamenBiologiqueService examenBiologiqueService,
                                      ExamenBiologiqueAssembler examenBiologiqueAssembler) {
        this.examenBiologiqueService = examenBiologiqueService;
        this.examenBiologiqueAssembler = examenBiologiqueAssembler;
    }

    @Override
    public ResponseEntity<ExamenBiologiqueDs> creerExamenBiologique(ExamenBiologiqueDs examenBiologiqueDs) {
        return new ResponseEntity<>(examenBiologiqueAssembler.assembleEntityToDs(
                examenBiologiqueService.saveExamenBiologique(examenBiologiqueAssembler.assembleExamenBiologiqueFromDs(examenBiologiqueDs))
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ExamenBiologiqueDs> updateExamenBiologique(Long id, ExamenBiologiqueDs examenBiologiqueDs) throws Exception {
        return new ResponseEntity<>(examenBiologiqueAssembler.assembleEntityToDs(
                examenBiologiqueService.updateExamenBiologique(id, examenBiologiqueAssembler.assembleExamenBiologiqueFromDs(examenBiologiqueDs))
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ExamenBiologiqueDs> findById(Long id) {
        return new ResponseEntity<>(examenBiologiqueAssembler.assembleEntityToDs(examenBiologiqueService.findById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ExamenBiologiqueDs>> findAllExamenBiologiques() {
        return new ResponseEntity<>(examenBiologiqueAssembler.assembleEntitiesFrom(examenBiologiqueService.findAllExamenBiologiques()), HttpStatus.OK);
    }

    @Override
    public void deleteExamenComplementaire(Long id) {
        examenBiologiqueService.deleteExamenBiologique(id);
    }

}
