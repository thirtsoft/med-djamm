package com.meddjamm.sn.rh.remote.controller;

import com.meddjamm.sn.rh.assembler.MedicamentAssembler;
import com.meddjamm.sn.rh.entity.Medicament;
import com.meddjamm.sn.rh.remote.controller.api.MedicamentApi;
import com.meddjamm.sn.rh.remote.model.MedicamentDs;
import com.meddjamm.sn.rh.services.MedicamentService;
import com.meddjamm.sn.utils.ResponseMassageDs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicamentController implements MedicamentApi {

    private final MedicamentService medicamentService;

    private final MedicamentAssembler medicamentAssembler;

    public MedicamentController(MedicamentService medicamentService,
                                MedicamentAssembler medicamentAssembler) {
        this.medicamentService = medicamentService;
        this.medicamentAssembler = medicamentAssembler;
    }

    @Override
    public ResponseMassageDs creerMedicament(MedicamentDs medicamentDs) {
        Medicament medicament = medicamentAssembler.assembleMedicamentFromDs(medicamentDs);
        try {
            Long id = medicamentService.saveMedicament(medicament);
            return new ResponseMassageDs("OK", id.toString());
        } catch (Exception e) {
            return new ResponseMassageDs("FAILED", e.getMessage());
        }
    }

    @Override
    public ResponseMassageDs updateMedicament(Long id, MedicamentDs medicamentDs) {
        Medicament medicament = medicamentAssembler.assembleMedicamentFromDs(medicamentDs);
        /*
        return new ResponseEntity<>(medicamentAssembler.assembleEntityToDs(
                medicamentService.updateMedicament(id, medicament)
        ), HttpStatus.OK);*/
        try {
            Long updatedMedicament = medicamentService.updateMedicament(id, medicament);
            return new ResponseMassageDs("OK", updatedMedicament.toString());
        } catch (Exception e) {
            return new ResponseMassageDs("FAILED", e.getMessage());
        }
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
        ), HttpStatus.OK);
    }

    @Override
    public void deleteMedicament(Long id) {
        medicamentService.deleteMedicament(id);
    }
}
