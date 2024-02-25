package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.AntecedentFamilialAssembler;
import com.meddjamm.sn.entity.AntecedentFamilial;
import com.meddjamm.sn.remote.controller.api.AntecedentFamilialApi;
import com.meddjamm.sn.remote.model.AntecedentFamilialDs;
import com.meddjamm.sn.services.AntecedentFamilialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class AntecedentFamilialController implements AntecedentFamilialApi {

    private final AntecedentFamilialService antecedentFamilialService;

    private final AntecedentFamilialAssembler antecedentFamilialAssembler;

    public AntecedentFamilialController(AntecedentFamilialService antecedentFamilialService,
                                        AntecedentFamilialAssembler antecedentFamilialAssembler) {
        this.antecedentFamilialService = antecedentFamilialService;
        this.antecedentFamilialAssembler = antecedentFamilialAssembler;
    }

    @Override
    public void creerAntecedentFamilial(AntecedentFamilialDs antecedentFamilialDs) {
        AntecedentFamilial antecedentFamilial = antecedentFamilialAssembler
                .assembleAntecedentFamilialFromDs(antecedentFamilialDs);
        antecedentFamilialService.saveAntecedentFamilial(antecedentFamilial);
    }

    @Override
    public void updateAntecedentFamilial(Long id, AntecedentFamilialDs antecedentFamilialDs) throws Exception {
        AntecedentFamilial antecedentFamilial = antecedentFamilialAssembler.assembleAntecedentFamilialFromDs(antecedentFamilialDs);
        antecedentFamilialService.updateAntecedentFamilial(id, antecedentFamilial);
    }

    @Override
    public ResponseEntity<AntecedentFamilialDs> findById(Long id) {
        return new ResponseEntity<>(antecedentFamilialAssembler
                .assembleEntityToDs(antecedentFamilialService.findById(id)), HttpStatus.OK);
    }

    @Override
    public void deleteAntecedentFamilialDs(Long id) {
        antecedentFamilialService.deleteAntecedentFamilial(id);
    }
}
