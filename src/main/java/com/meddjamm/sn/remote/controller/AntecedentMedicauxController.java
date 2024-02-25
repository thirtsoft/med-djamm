package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.AntecedentMedicauxAssembler;
import com.meddjamm.sn.entity.AntecedentMedicaux;
import com.meddjamm.sn.remote.controller.api.AntecedentMedicauxApi;
import com.meddjamm.sn.remote.model.AntecedentMedicauxDs;
import com.meddjamm.sn.services.AntecedentMedicauxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class AntecedentMedicauxController implements AntecedentMedicauxApi {

    private final AntecedentMedicauxService antecedentMedicauxService;

    private final AntecedentMedicauxAssembler antecedentMedicauxAssembler;

    public AntecedentMedicauxController(AntecedentMedicauxService antecedentMedicauxService,
                                        AntecedentMedicauxAssembler antecedentMedicauxAssembler) {
        this.antecedentMedicauxService = antecedentMedicauxService;
        this.antecedentMedicauxAssembler = antecedentMedicauxAssembler;
    }

    @Override
    public void creerAntecedentMedicaux(AntecedentMedicauxDs antecedentMedicauxDs) {
        AntecedentMedicaux antecedentMedicaux = antecedentMedicauxAssembler.assembleAntecedentMedicauxFromDs(antecedentMedicauxDs);
        antecedentMedicauxService.saveAntecedentMedicaux(antecedentMedicaux);
    }

    @Override
    public void updateAntecedentMedicauxDs(Long id, AntecedentMedicauxDs antecedentMedicauxDs) throws Exception {

    }

    @Override
    public ResponseEntity<AntecedentMedicauxDs> findById(Long id) {
        return new ResponseEntity<>(antecedentMedicauxAssembler
                .assembleEntityToDs(antecedentMedicauxService.findById(id)),
                HttpStatus.OK);
    }

    @Override
    public void deleteAntecedentMedicauxDs(Long id) {
        antecedentMedicauxService.deleteAntecedentMedicaux(id);
    }
}
