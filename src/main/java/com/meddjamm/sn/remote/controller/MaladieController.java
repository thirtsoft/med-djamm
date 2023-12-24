package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.MaladieAssembler;
import com.meddjamm.sn.entity.Maladie;
import com.meddjamm.sn.remote.controller.api.MaladieApi;
import com.meddjamm.sn.remote.model.MaladieDs;
import com.meddjamm.sn.services.MaladieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class MaladieController implements MaladieApi {

    private final MaladieService maladieService;
    private final MaladieAssembler maladieAssembler;

    public MaladieController(MaladieService maladieService, MaladieAssembler maladieAssembler) {
        this.maladieService = maladieService;
        this.maladieAssembler = maladieAssembler;
    }

    @Override
    public ResponseEntity<Maladie> creerMaladie(Maladie maladie) {
        Maladie maladieResult = maladieService.saveMaladie(maladie);
        return new ResponseEntity<>(maladieResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Maladie> updateMaladie(Long id, Maladie maladie) throws Exception {
        Maladie maladieResult = maladieService.updateMaladie(id, maladie);
        return new ResponseEntity<>(maladieResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MaladieDs> findById(Long id) {
        MaladieDs maladieResult = maladieAssembler.assembleEntityToDs(maladieService.findById(id));
        return new ResponseEntity<>(maladieResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MaladieDs>> findAllMaladies() {
        List<MaladieDs> maladieList = maladieService.findAllMaladies()
                .stream()
                .map(maladieAssembler::assembleEntityToDs)
                .toList();
        return new ResponseEntity<>(maladieList, HttpStatus.OK);
    }

    @Override
    public void deleteMaladie(Long id) {
        maladieService.deleteMaladie(id);
    }
}
