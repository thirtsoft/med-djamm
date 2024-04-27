package com.meddjamm.sn.dossiermedical.remote.controller;

import com.meddjamm.sn.dossiermedical.assembler.AvisSpecialisteAssembler;
import com.meddjamm.sn.dossiermedical.remote.controller.api.AvisSpecialisteApi;
import com.meddjamm.sn.dossiermedical.remote.model.AvisSpecialisteDs;
import com.meddjamm.sn.dossiermedical.services.AvisSpecialisteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AvisSpecialisteController implements AvisSpecialisteApi {

    private final AvisSpecialisteService avisSpecialisteService;

    private final AvisSpecialisteAssembler avisSpecialisteAssembler;

    public AvisSpecialisteController(AvisSpecialisteService avisSpecialisteService,
                                     AvisSpecialisteAssembler avisSpecialisteAssembler) {
        this.avisSpecialisteService = avisSpecialisteService;
        this.avisSpecialisteAssembler = avisSpecialisteAssembler;
    }

    @Override
    public ResponseEntity<AvisSpecialisteDs> creerAvisSpecialiste(AvisSpecialisteDs avisSpecialisteDs) {
        return new ResponseEntity<>(avisSpecialisteAssembler.assembleEntityToDs(
                avisSpecialisteService.saveAvisSpecialiste(
                        avisSpecialisteAssembler.assembleAvisSpecialisteFromDs(avisSpecialisteDs)
                )
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AvisSpecialisteDs> updateAvisSpecialiste(Long id, AvisSpecialisteDs avisSpecialisteDs) throws Exception {
        return new ResponseEntity<>(avisSpecialisteAssembler.assembleEntityToDs(
                avisSpecialisteService.updateAvisSpecialiste(id,
                        avisSpecialisteAssembler.assembleUpdateAvisSpecialisteFromDs(avisSpecialisteDs)
                )
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AvisSpecialisteDs> findById(Long id) {
        return new ResponseEntity<>(avisSpecialisteAssembler.assembleEntityToDs(
                avisSpecialisteService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AvisSpecialisteDs>> findAllAvisSpecialistes() {
        return new ResponseEntity<>(avisSpecialisteAssembler.assembleEntitiesFrom(
                avisSpecialisteService.findAllAvisSpecialistes()
        ), HttpStatus.OK);
    }

    @Override
    public void deleteAvisSpecialiste(Long id) {
        avisSpecialisteService.deleteAvisSpecialiste(id);
    }

    @Override
    public ResponseEntity<List<AvisSpecialisteDs>> findAllAvisSpecialistesByPatientId(String code) {
        return new ResponseEntity<>(avisSpecialisteAssembler.assembleEntitiesFrom(
                avisSpecialisteService.findAvisSpecialisteByPatientId(code)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AvisSpecialisteDs>> findAllAvisSpecialistesByCircuitId(Long code) {
        return new ResponseEntity<>(avisSpecialisteAssembler.assembleEntitiesFrom(
                avisSpecialisteService.findAvisSpecialisteByCircuitId(code)
        ), HttpStatus.OK);
    }
}
