package com.meddjamm.sn.controller;

import com.meddjamm.sn.controller.api.MaladieApi;
import com.meddjamm.sn.model.Maladie;
import com.meddjamm.sn.services.MaladieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class MaladieController implements MaladieApi {

    private final MaladieService maladieService;

    public MaladieController(MaladieService maladieService) {
        this.maladieService = maladieService;
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
    public ResponseEntity<Maladie> findById(Long id) {
        Maladie maladieResult = maladieService.findById(id);
        return new ResponseEntity<>(maladieResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Maladie>> findAllMaladies() {
        List<Maladie> maladieList = maladieService.findAllMaladies();
        return new ResponseEntity<>(maladieList, HttpStatus.OK);
    }

    @Override
    public void deleteMaladie(Long id) {
        maladieService.deleteMaladie(id);
    }
}
