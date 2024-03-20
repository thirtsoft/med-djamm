package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.MedecinAssembler;
import com.meddjamm.sn.config.entity.Medecin;
import com.meddjamm.sn.remote.controller.api.MedecinApi;
import com.meddjamm.sn.remote.model.MedecinDetailDs;
import com.meddjamm.sn.config.remote.model.MedecinDs;
import com.meddjamm.sn.services.MedecinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class MedecinController implements MedecinApi {

    private final MedecinService medecinService;

    private final MedecinAssembler medecinAssembler;

    public MedecinController(MedecinService medecinService,
                             MedecinAssembler medecinAssembler) {
        this.medecinService = medecinService;
        this.medecinAssembler = medecinAssembler;
    }

    @Override
    public ResponseEntity<MedecinDs> creerMedecin(MedecinDs medecinDs) {
        Medecin medecin = medecinAssembler.assembleMedecinFromDs(medecinDs);
        return new ResponseEntity<>(medecinAssembler.assembleEntityToDs(
                medecinService.saveMedecin(medecin)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MedecinDs> updateMedecin(Long id, MedecinDs medecinDs) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<MedecinDetailDs> findById(Long id) {
        return new ResponseEntity<>(medecinAssembler.assembleEntitiesToDs(
                medecinService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MedecinDetailDs>> findAllMedecins() {
        List<MedecinDetailDs> medecinList = medecinAssembler.assembleEntitiesFrom(medecinService.findAllMedecins());
        return new ResponseEntity<>(medecinList, HttpStatus.OK);
    }

    @Override
    public void deleteMedecin(Long id) {
        medecinService.deleteMedecin(id);
    }
}
