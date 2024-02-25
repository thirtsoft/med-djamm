package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.AntecedentGynecologieAssembler;
import com.meddjamm.sn.entity.AntecedentGynecologie;
import com.meddjamm.sn.remote.controller.api.AntecedentGynecologieApi;
import com.meddjamm.sn.remote.model.AntecedentGynecologieDs;
import com.meddjamm.sn.services.AntecedentGynecologieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class AntecedentGynecologieController implements AntecedentGynecologieApi {

    private final AntecedentGynecologieService antecedentGynecologieService;

    private final AntecedentGynecologieAssembler antecedentGynecologieAssembler;

    public AntecedentGynecologieController(AntecedentGynecologieService antecedentGynecologieService,
                                           AntecedentGynecologieAssembler antecedentGynecologieAssembler) {
        this.antecedentGynecologieService = antecedentGynecologieService;
        this.antecedentGynecologieAssembler = antecedentGynecologieAssembler;
    }


    @Override
    public void creerAntecedentGynecologie(AntecedentGynecologieDs antecedentGynecologieDs) {
        AntecedentGynecologie antecedentGynecologie = antecedentGynecologieAssembler
                .assembleAntecedentGynecologieFromDs(antecedentGynecologieDs);
        antecedentGynecologieService.saveAntecedentGynecologie(antecedentGynecologie);
    }

    @Override
    public void updateAntecedentGynecologie(Long id, AntecedentGynecologieDs antecedentGynecologieDs) throws Exception {
        AntecedentGynecologie antecedentGynecologie = antecedentGynecologieAssembler.assembleAntecedentGynecologieFromDs(antecedentGynecologieDs);
        antecedentGynecologieService.updateAntecedentGynecologie(id, antecedentGynecologie);
    }

    @Override
    public ResponseEntity<AntecedentGynecologieDs> findById(Long id) {
        return new ResponseEntity<>(antecedentGynecologieAssembler
                .assembleEntityToDs(antecedentGynecologieService.findById(id)),
                HttpStatus.OK);
    }

    @Override
    public void deleteAntecedentGynecologie(Long id) {
        antecedentGynecologieService.deleteAntecedentGynecologie(id);
    }
}
