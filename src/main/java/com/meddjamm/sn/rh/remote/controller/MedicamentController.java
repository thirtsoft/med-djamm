package com.meddjamm.sn.rh.remote.controller;

import com.meddjamm.sn.rh.assembler.MedicamentAssembler;
import com.meddjamm.sn.rh.entity.Medicament;
import com.meddjamm.sn.rh.remote.controller.api.MedicamentApi;
import com.meddjamm.sn.rh.remote.model.MedicamentDs;
import com.meddjamm.sn.rh.services.MedicamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class MedicamentController implements MedicamentApi {

    private final MedicamentService medicamentService;

    private final MedicamentAssembler medicamentAssembler;

    public MedicamentController(MedicamentService medicamentService,
                                MedicamentAssembler medicamentAssembler) {
        this.medicamentService = medicamentService;
        this.medicamentAssembler = medicamentAssembler;
    }

    @Override
    public ResponseEntity<MedicamentDs> creerMedicament(MedicamentDs medicamentDs) {
        Medicament medicament = medicamentAssembler.assembleMedicamentFromDs(medicamentDs);
        return new ResponseEntity<>(medicamentAssembler.assembleEntityToDs(
                medicamentService.saveMedicament(medicament)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MedicamentDs> updateMedicament(Long id, MedicamentDs medicamentDs) throws Exception {
        Medicament medicament = medicamentAssembler.assembleMedicamentFromDs(medicamentDs);
        return new ResponseEntity<>(medicamentAssembler.assembleEntityToDs(
                medicamentService.updateMedicament(id, medicament)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MedicamentDs> findById(Long id) {
        return new ResponseEntity<>(medicamentAssembler.assembleEntityToDs(
                medicamentService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MedicamentDs>> findAllMedicaments() {
        return new ResponseEntity<>(medicamentAssembler.assembleEntitiesFrom(
                medicamentService.findAllMedicaments()
        ),HttpStatus.OK);
    }

    @Override
    public void deleteMedicament(Long id) {
        medicamentService.deleteMedicament(id);
    }
}
