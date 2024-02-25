package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.AtteinteAssembler;
import com.meddjamm.sn.entity.Atteinte;
import com.meddjamm.sn.remote.controller.api.AtteinteApi;
import com.meddjamm.sn.remote.model.AtteinteDs;
import com.meddjamm.sn.services.AtteinteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AtteinteController implements AtteinteApi {

    private final AtteinteService atteinteService;

    private final AtteinteAssembler atteinteAssembler;

    public AtteinteController(AtteinteService atteinteService,
                              AtteinteAssembler atteinteAssembler) {
        this.atteinteService = atteinteService;
        this.atteinteAssembler = atteinteAssembler;
    }

    @Override
    public void creerAtteinte(AtteinteDs atteinteDs) {
        Atteinte atteinte = atteinteAssembler.assembleAtteinteFromDs(atteinteDs);
        atteinteService.saveAtteinte(atteinte);
    }

    @Override
    public ResponseEntity<AtteinteDs> updateAtteinte(Long id, AtteinteDs atteinteDs) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<AtteinteDs> findById(Long id) {
        Atteinte atteinte = atteinteService.findById(id);
        return new ResponseEntity<>(atteinteAssembler.assembleEntityToDs(atteinte), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AtteinteDs>> findAllAtteintes() {
        List<Atteinte> Atteintes = atteinteService.findAllAtteintes();
        return new ResponseEntity<>(atteinteAssembler.assembleEntitiesFrom(Atteintes), HttpStatus.OK);
    }

    @Override
    public void deleteAtteinte(Long id) {
        atteinteService.deleteAtteinte(id);
    }
}
