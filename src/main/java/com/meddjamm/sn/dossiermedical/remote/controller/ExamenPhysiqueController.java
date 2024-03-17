package com.meddjamm.sn.dossiermedical.remote.controller;

import com.meddjamm.sn.dossiermedical.assembler.ExamenPhysiqueAssembler;
import com.meddjamm.sn.dossiermedical.remote.controller.api.ExamenPhysiqueApi;
import com.meddjamm.sn.dossiermedical.remote.model.ExamenPhysiqueDs;
import com.meddjamm.sn.dossiermedical.services.ExamenPhysiqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExamenPhysiqueController implements ExamenPhysiqueApi {

    private final ExamenPhysiqueService examenPhysiqueService;

    private final ExamenPhysiqueAssembler examenPhysiqueAssembler;

    public ExamenPhysiqueController(ExamenPhysiqueService examenPhysiqueService,
                                    ExamenPhysiqueAssembler examenPhysiqueAssembler) {
        this.examenPhysiqueService = examenPhysiqueService;
        this.examenPhysiqueAssembler = examenPhysiqueAssembler;
    }

    @Override
    public ResponseEntity<ExamenPhysiqueDs> creerExamenPhysique(ExamenPhysiqueDs examenPhysiqueDs) {
        return new ResponseEntity<>(examenPhysiqueAssembler.assembleEntityToDs(
                examenPhysiqueService.saveExamenPhysique(examenPhysiqueAssembler.assembleExamenPhysiqueFromDs(examenPhysiqueDs))
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ExamenPhysiqueDs> updateExamenPhysique(Long id, ExamenPhysiqueDs examenPhysiqueDs) throws Exception {
        return new ResponseEntity<>(examenPhysiqueAssembler.assembleEntityToDs(
                examenPhysiqueService.updateExamenPhysique(id, examenPhysiqueAssembler.assembleExamenPhysiqueFromDs(examenPhysiqueDs))
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ExamenPhysiqueDs> findById(Long id) {
        return new ResponseEntity<>(examenPhysiqueAssembler.assembleEntityToDs(
                examenPhysiqueService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ExamenPhysiqueDs>> findAllExamenPhysiques() {
        return new ResponseEntity<>(examenPhysiqueAssembler.assembleEntitiesFrom(examenPhysiqueService.findAllExamenPhysiques()), HttpStatus.OK);
    }

    @Override
    public void deleteExamenPhysique(Long id) {
        examenPhysiqueService.deleteExamenPhysique(id);
    }
}
