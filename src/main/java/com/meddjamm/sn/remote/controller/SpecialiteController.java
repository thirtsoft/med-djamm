package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.SpecialiteAssembler;
import com.meddjamm.sn.entity.Specialite;
import com.meddjamm.sn.remote.controller.api.SpecialiteApi;
import com.meddjamm.sn.remote.model.SpecialiteDs;
import com.meddjamm.sn.services.SpecialiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class SpecialiteController implements SpecialiteApi {

    private final SpecialiteService specialiteService;

    private final SpecialiteAssembler specialiteAssembler;

    public SpecialiteController(SpecialiteService specialiteService,
                                SpecialiteAssembler specialiteAssembler) {
        this.specialiteService = specialiteService;
        this.specialiteAssembler = specialiteAssembler;
    }

    @Override
    public ResponseEntity<?> creerSpeciality(SpecialiteDs specialiteDs) {
        Specialite specialite = specialiteAssembler.assembleSpecialiteFromDs(specialiteDs);
        specialiteService.addSpeciality(specialite);
        return new ResponseEntity<>(specialite, HttpStatus.CREATED);

    }

    @Override
    public void updateSpeciality(Long id, SpecialiteDs specialiteDs) {
        Specialite specialite = specialiteAssembler.assembleSpecialiteFromDs(specialiteDs);
        specialiteService.updateSpeciality(id, specialite);
    }

    @Override
    public ResponseEntity<SpecialiteDs> findSpecialityById(Long id) {
        return new ResponseEntity<>(specialiteAssembler.assembleSpecialiteFrom(
                specialiteService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SpecialiteDs>> getAllSpecialities() {
        return new ResponseEntity<>(specialiteAssembler.assembleEntitiesFrom(
                specialiteService.findAllSpecialities()
        ), HttpStatus.OK);
    }

    @Override
    public void deleteSpeciality(Long id) {
        specialiteService.deleteSpeciality(id);
    }
}
