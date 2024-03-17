package com.meddjamm.sn.dossiermedical.remote.controller;

import com.meddjamm.sn.dossiermedical.assembler.SyntheseAssembler;
import com.meddjamm.sn.dossiermedical.remote.controller.api.SyntheseApi;
import com.meddjamm.sn.dossiermedical.remote.model.SyntheseDs;
import com.meddjamm.sn.dossiermedical.services.SyntheseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SyntheseController implements SyntheseApi {

    private final SyntheseService syntheseService;

    private final SyntheseAssembler syntheseAssembler;

    public SyntheseController(SyntheseService syntheseService,
                              SyntheseAssembler syntheseAssembler) {
        this.syntheseService = syntheseService;
        this.syntheseAssembler = syntheseAssembler;
    }

    @Override
    public ResponseEntity<SyntheseDs> creerSynthese(SyntheseDs syntheseDs) {
        return new ResponseEntity<>(syntheseAssembler.assembleEntityToDs(
                syntheseService.saveSynthese(
                        syntheseAssembler.assembleSyntheseFromDs(syntheseDs)
                )
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SyntheseDs> updateSynthese(Long id, SyntheseDs syntheseDs) throws Exception {
        return new ResponseEntity<>(syntheseAssembler.assembleEntityToDs(
                syntheseService.updateSynthese(id,
                        syntheseAssembler.assembleSyntheseFromDs(syntheseDs)
                )
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SyntheseDs> findById(Long id) {
        return new ResponseEntity<>(syntheseAssembler.assembleEntityToDs(
                syntheseService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SyntheseDs>> findAllSyntheses() {
        return new ResponseEntity<>(syntheseAssembler.assembleEntitiesFrom(
                syntheseService.findAllSyntheses()
        ), HttpStatus.OK);
    }

    @Override
    public void deleteSynthese(Long id) {
        syntheseService.deleteSynthese(id);
    }

    @Override
    public ResponseEntity<List<SyntheseDs>> findAllSynthesesByPatientId(String code) {
        return new ResponseEntity<>(syntheseAssembler.assembleEntitiesFrom(
                syntheseService.findSynthesesByPatientId(code)
        ), HttpStatus.OK);
    }
}
