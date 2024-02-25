package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.AntecedentChirurgieAssembler;
import com.meddjamm.sn.entity.AntecedentChirurgie;
import com.meddjamm.sn.remote.controller.api.AntecedentChirurgieApi;
import com.meddjamm.sn.remote.model.AntecedentChirurgieDs;
import com.meddjamm.sn.services.AntecedentChirurgieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class AntecedentChirurgieController implements AntecedentChirurgieApi {

    private final AntecedentChirurgieService antecedentChirurgieService;

    private final AntecedentChirurgieAssembler antecedentChirurgieAssembler;

    public AntecedentChirurgieController(AntecedentChirurgieService antecedentChirurgieService,
                                         AntecedentChirurgieAssembler antecedentChirurgieAssembler) {
        this.antecedentChirurgieService = antecedentChirurgieService;
        this.antecedentChirurgieAssembler = antecedentChirurgieAssembler;
    }

    @Override
    public void creerAntecedentChirurgie(AntecedentChirurgieDs antecedentChirurgieDs) {
        AntecedentChirurgie antecedentChirurgieAjouter = antecedentChirurgieAssembler.assembleAntecedentChirurgieFromDs(antecedentChirurgieDs);
        antecedentChirurgieService.saveAntecedentChirurgie(antecedentChirurgieAjouter);
    }

    @Override
    public void updateAntecedentChirurgie(Long id, AntecedentChirurgieDs antecedentChirurgieDs) throws Exception {
        AntecedentChirurgie antecedentChirurgieModifier = antecedentChirurgieAssembler.assembleAntecedentChirurgieFromDs(antecedentChirurgieDs);
        antecedentChirurgieService.updateAntecedentChirurgie(id, antecedentChirurgieModifier);
    }

    @Override
    public ResponseEntity<AntecedentChirurgieDs> findById(Long id) {
        return new ResponseEntity<>(antecedentChirurgieAssembler
                .assembleEntityToDs(antecedentChirurgieService.findById(id)), HttpStatus.OK);
    }

    @Override
    public void deleteAntecedentChirurgie(Long id) {
        antecedentChirurgieService.deleteAntecedentChirurgie(id);
    }
}
