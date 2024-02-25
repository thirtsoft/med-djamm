package com.meddjamm.sn.dossiermedical.remote.controller;

import com.meddjamm.sn.dossiermedical.assembler.RendezVousAssembler;
import com.meddjamm.sn.dossiermedical.entity.RendezVous;
import com.meddjamm.sn.dossiermedical.remote.controller.api.RendezVousApi;
import com.meddjamm.sn.dossiermedical.remote.model.RendezVousDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.RendezVousDs;
import com.meddjamm.sn.dossiermedical.services.RendezVousService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class RendezVousController implements RendezVousApi {

    private final RendezVousService rendezVousService;

    private final RendezVousAssembler rendezVousAssembler;

    public RendezVousController(RendezVousService rendezVousService, RendezVousAssembler rendezVousAssembler) {
        this.rendezVousService = rendezVousService;
        this.rendezVousAssembler = rendezVousAssembler;
    }

    @Override
    public ResponseEntity<RendezVousDetailDs> creerRendezVousDs(RendezVousDs rendezVousDs) {
        RendezVous rendezVousAjouter = rendezVousAssembler.assembleRendezVoRendezVoususFromDs(rendezVousDs);
        return new ResponseEntity<>(rendezVousAssembler.assembleEntitiesToDs(rendezVousService.saveRendezVous(rendezVousAjouter)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RendezVousDetailDs> updateRendezVousDs(Long id, RendezVousDs rendezVousDs) throws Exception {
        RendezVous rendezVousModifier = rendezVousAssembler.assembleRendezVoRendezVoususFromDs(rendezVousDs);
        return new ResponseEntity<>(rendezVousAssembler.assembleEntitiesToDs(rendezVousService.updateRendezVous(id, rendezVousModifier)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RendezVousDetailDs> findById(Long id) {
        RendezVousDetailDs rendezVousResult = rendezVousAssembler.assembleEntitiesToDs(rendezVousService.findById(id));
        return new ResponseEntity<>(rendezVousResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RendezVousDetailDs>> findAllRendezVous() {
        List<RendezVousDetailDs> rendezVousDsList = rendezVousService.findAllRendezVouss()
                .stream()
                .map(rendezVousAssembler::assembleEntitiesToDs)
                .toList();
        return new ResponseEntity<>(rendezVousDsList, HttpStatus.OK);
    }

    @Override
    public void deleteRendezVous(Long id) {
        rendezVousService.deleteRendezVous(id);
    }

    @Override
    public ResponseEntity<List<RendezVousDetailDs>> findByDoctorId(String matricule) {
        List<RendezVousDetailDs> rendezVousDsList = rendezVousService.findRendezVousByDoctorId(matricule)
                .stream()
                .map(rendezVousAssembler::assembleEntitiesToDs)
                .toList();
        return new ResponseEntity<>(rendezVousDsList, HttpStatus.OK);
    }
}
