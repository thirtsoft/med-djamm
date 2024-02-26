package com.meddjamm.sn.dossiermedical.remote.controller;

import com.meddjamm.sn.dossiermedical.assembler.ObservationCliniqueAssembler;
import com.meddjamm.sn.dossiermedical.entity.ObservationClinique;
import com.meddjamm.sn.dossiermedical.remote.controller.api.ObservationCliniqueApi;
import com.meddjamm.sn.dossiermedical.remote.model.ObservationCliniqueDs;
import com.meddjamm.sn.dossiermedical.services.ObservationCliniqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200/")
public class ObservationCliniqueController implements ObservationCliniqueApi {

    private final ObservationCliniqueAssembler observationCliniqueAssembler;
    private final ObservationCliniqueService observationCliniqueService;

    public ObservationCliniqueController(ObservationCliniqueAssembler observationCliniqueAssembler,
                                         ObservationCliniqueService observationCliniqueService) {
        this.observationCliniqueAssembler = observationCliniqueAssembler;
        this.observationCliniqueService = observationCliniqueService;
    }

    @Override
    public void creerObservationClinique(ObservationCliniqueDs observationCliniqueDs) {
        ObservationClinique observationClinique = observationCliniqueAssembler.assembleObservationCliniqueFromDs(
                observationCliniqueDs
        );
        observationCliniqueService.saveObservationClinique(observationClinique);
    }

    @Override
    public void updateObservationClinique(Long id, ObservationCliniqueDs observationCliniqueDs) throws Exception {
        ObservationClinique observationClinique = observationCliniqueAssembler.assembleObservationCliniqueFromDs(
                observationCliniqueDs
        );
        observationCliniqueService.updateObservationClinique(id, observationClinique);
    }

    @Override
    public ResponseEntity<ObservationCliniqueDs> findById(Long id) {
        return new ResponseEntity<>(observationCliniqueAssembler.assembleEntityToDs(
                observationCliniqueService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ObservationCliniqueDs>> findObservationCliniques() {
        return new ResponseEntity<>(observationCliniqueAssembler.assembleEntitiesFrom(
                observationCliniqueService.findAllObservationCliniques()
        ), HttpStatus.OK);
    }

    @Override
    public void deleteObservationClinique(Long id) {
        observationCliniqueService.deleteObservationClinique(id);
    }

    @Override
    public ResponseEntity<List<ObservationCliniqueDs>> findObservationCliniqueByPatientId(String code) {
        return new ResponseEntity<>(observationCliniqueAssembler.assembleEntitiesFrom(
                observationCliniqueService.findObservationCliniqueByPatientId(code)
        ), HttpStatus.OK);
    }
}
