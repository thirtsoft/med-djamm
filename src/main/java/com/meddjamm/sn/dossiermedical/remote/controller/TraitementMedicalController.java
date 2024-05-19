package com.meddjamm.sn.dossiermedical.remote.controller;

import com.meddjamm.sn.dossiermedical.assembler.TraitementMedicalAssembler;
import com.meddjamm.sn.dossiermedical.entity.TraitementMedical;
import com.meddjamm.sn.dossiermedical.remote.controller.api.TraitementMedicalApi;
import com.meddjamm.sn.dossiermedical.remote.model.TraitementMedicalDs;
import com.meddjamm.sn.dossiermedical.services.TraitementMedicalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TraitementMedicalController implements TraitementMedicalApi {

    private final TraitementMedicalAssembler traitementMedicalAssembler;

    private final TraitementMedicalService traitementMedicalService;

    public TraitementMedicalController(TraitementMedicalAssembler traitementMedicalAssembler,
                                       TraitementMedicalService traitementMedicalService) {
        this.traitementMedicalAssembler = traitementMedicalAssembler;
        this.traitementMedicalService = traitementMedicalService;
    }

    @Override
    public ResponseEntity<TraitementMedicalDs> creerTraitementMedical(TraitementMedicalDs traitementMedicalDs) {
        return new ResponseEntity<>(traitementMedicalAssembler.assembleEntityToDs(
                traitementMedicalService.saveTraitementMedical(
                        traitementMedicalAssembler.assembleTraitementMedicalFromDs(traitementMedicalDs))
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TraitementMedicalDs> updateTraitementMedical(Long id, TraitementMedicalDs traitementMedicalDs) throws Exception {
        TraitementMedical traitementMedical = traitementMedicalAssembler.assembleTraitementMedicalFromDs(traitementMedicalDs);
        return new ResponseEntity<>(traitementMedicalAssembler.assembleEntityToDs(
                traitementMedicalService.updateTraitementMedical(id, traitementMedical)
        ), HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<TraitementMedicalDs> findTraitementMedicalById(Long id) {
        return new ResponseEntity<>(traitementMedicalAssembler.assembleEntityToDs(
                traitementMedicalService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TraitementMedicalDs>> findAllTraitementMedicals() {
        return new ResponseEntity<>(traitementMedicalAssembler.assembleEntitiesFrom(
                traitementMedicalService.findAllTraitementMedicals()
        ), HttpStatus.OK);
    }

    @Override
    public void deleteTraitementMedical(Long id) {
        traitementMedicalService.deleteTraitementMedical(id);
    }

}
