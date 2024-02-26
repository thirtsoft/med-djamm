package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.OrdonnanceAssembler;
import com.meddjamm.sn.entity.Ordonnance;
import com.meddjamm.sn.remote.controller.api.OrdonnanceApi;
import com.meddjamm.sn.remote.model.OrdonnanceDetailDs;
import com.meddjamm.sn.remote.model.OrdonnanceDs;
import com.meddjamm.sn.services.OrdonnanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class OrdonnanceController implements OrdonnanceApi {

    private final OrdonnanceService ordonnanceService;

    private final OrdonnanceAssembler ordonnanceAssembler;

    public OrdonnanceController(OrdonnanceService ordonnanceService,
                                OrdonnanceAssembler ordonnanceAssembler) {
        this.ordonnanceService = ordonnanceService;
        this.ordonnanceAssembler = ordonnanceAssembler;
    }

    @Override
    public ResponseEntity<OrdonnanceDetailDs> creerOrdonnance(OrdonnanceDs ordonnanceDs) {
        Ordonnance ordonnance = ordonnanceAssembler.assembleOrdonnanceFromDs(ordonnanceDs);
        //   ordonnanceService.saveOrdonnance(ordonnance);
        return new ResponseEntity<>(ordonnanceAssembler.assembleEntitiesToDs(
                ordonnanceService.saveOrdonnance(ordonnance)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrdonnanceDetailDs> updateOrdonnance(Long id, OrdonnanceDs ordonnanceDs) throws Exception {
        Ordonnance ordonnance = ordonnanceAssembler.assembleOrdonnanceFromDs(ordonnanceDs);
        return new ResponseEntity<>(ordonnanceAssembler.assembleEntitiesToDs(
                ordonnanceService.updateOrdonnance(id, ordonnance)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrdonnanceDetailDs> findById(Long id) {
        return new ResponseEntity<>(ordonnanceAssembler.assembleEntitiesToDs(
                ordonnanceService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrdonnanceDetailDs>> findAllOrdonnances() {
        return new ResponseEntity<>(ordonnanceAssembler.assembleEntitiesFrom(
                ordonnanceService.findAllOrdonnances()
        ), HttpStatus.OK);
    }

    @Override
    public void deleteOrdonnance(Long id) {
        ordonnanceService.deleteOrdonnance(id);
    }
}
