package com.meddjamm.sn.dossiermedical.remote.controller;

import com.meddjamm.sn.dossiermedical.assembler.OrdonnanceAssembler;
import com.meddjamm.sn.dossiermedical.remote.controller.api.OrdonnanceApi;
import com.meddjamm.sn.dossiermedical.remote.model.OrdonnanceDs;
import com.meddjamm.sn.dossiermedical.services.OrdonnanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdonnanceController implements OrdonnanceApi {

    private final OrdonnanceService ordonnanceService;

    private final OrdonnanceAssembler ordonnanceAssembler;

    public OrdonnanceController(OrdonnanceService ordonnanceService,
                                OrdonnanceAssembler ordonnanceAssembler) {
        this.ordonnanceService = ordonnanceService;
        this.ordonnanceAssembler = ordonnanceAssembler;
    }


    @Override
    public ResponseEntity<OrdonnanceDs> creerOrdonnance(OrdonnanceDs ordonnanceDs) {
        return new ResponseEntity<>(ordonnanceAssembler.assembleEntityToDs(
                ordonnanceService.saveOrdonnance(ordonnanceAssembler.assembleOrdonnanceFromDs(
                        ordonnanceDs
                ))
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrdonnanceDs> updateOrdonnance(Long id, OrdonnanceDs ordonnanceDs) throws Exception {
        return new ResponseEntity<>(ordonnanceAssembler.assembleEntityToDs(
                ordonnanceService.updateOrdonnance(id, ordonnanceAssembler.assembleUpdateOrdonnanceFromDs(
                        ordonnanceDs
                ))
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrdonnanceDs> findById(Long id) {
        return new ResponseEntity<>(ordonnanceAssembler.assembleEntityToDs(ordonnanceService.findById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrdonnanceDs>> findAllOrdonnances() {
        return new ResponseEntity<>(ordonnanceAssembler.assembleEntitiesFrom(ordonnanceService.findAllOrdonnances()), HttpStatus.OK);
    }

    @Override
    public void deleteOrdonnance(Long id) {
        ordonnanceService.deleteOrdonnance(id);
    }

    @Override
    public ResponseEntity<List<OrdonnanceDs>> findAllOrdonnancesByPatientId(String code) {
        return new ResponseEntity<>(ordonnanceAssembler.assembleEntitiesFrom(ordonnanceService.findOrdonnancesByPatientId(code)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrdonnanceDs>> findAllOrdonnancesByCircuitId(Long code) {
        return new ResponseEntity<>(ordonnanceAssembler.assembleEntitiesFrom(ordonnanceService.findOrdonnancesByCircuitId(code)), HttpStatus.OK);
    }
}
