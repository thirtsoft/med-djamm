package com.meddjamm.sn.rh.remote.controller;

import com.meddjamm.sn.rh.assembler.RendezVousAssembler;
import com.meddjamm.sn.rh.entity.RendezVous;
import com.meddjamm.sn.rh.remote.controller.api.RendezVousApi;
import com.meddjamm.sn.rh.remote.model.RendezVousDeplaceDs;
import com.meddjamm.sn.rh.remote.model.RendezVousDetailDs;
import com.meddjamm.sn.rh.remote.model.RendezVousDs;
import com.meddjamm.sn.rh.services.RendezVousService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
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
    public ResponseEntity<List<RendezVousDetailDs>> findByDoctorId(Long matricule) {
        List<RendezVousDetailDs> rendezVousDsList = rendezVousService.findAllRendezVousByDoctorId(matricule)
                .stream()
                .map(rendezVousAssembler::assembleEntitiesToDs)
                .toList();
        return new ResponseEntity<>(rendezVousDsList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RendezVousDetailDs>> findTreeLatestRendezVousByPatient(Long patientId) {
        List<RendezVousDetailDs> rendezVousDsList = rendezVousService.findTreeLatestRendezVousByPatient(patientId)
                .stream()
                .map(rendezVousAssembler::assembleEntitiesToDs)
                .toList();
        return new ResponseEntity<>(rendezVousDsList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RendezVousDetailDs>> getAllRendezVousInDay() {
        List<RendezVousDetailDs> rendezVousDsList = rendezVousService.findRendezVousDuJours()
                .stream()
                .map(rendezVousAssembler::assembleEntitiesToDs)
                .toList();
        return new ResponseEntity<>(rendezVousDsList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RendezVousDetailDs>> findAllRendezVousOfDoctorInMonth(Long matricule) {
        List<RendezVousDetailDs> rendezVousDsList = rendezVousService.findAllRendezVousOfDoctorInMonth(matricule)
                .stream()
                .map(rendezVousAssembler::assembleEntitiesToDs)
                .toList();
        return new ResponseEntity<>(rendezVousDsList, HttpStatus.OK);
    }

    @Override
    public void DeplacerRendezVous(RendezVousDeplaceDs rendezVousDeplace) {
        RendezVous rendezVousModifier = rendezVousAssembler.assembleEntitiesToDeplaceRendezVous(rendezVousDeplace);
        rendezVousService.deplacezRendezVous(rendezVousModifier);
    }

    @Override
    public ResponseEntity<List<RendezVousDetailDs>> findRendezVousBySelectedDate(Date dateRv) {
        List<RendezVousDetailDs> rendezVousDsList = rendezVousService.findRendezVousBySelectedDate(dateRv)
                .stream()
                .map(rendezVousAssembler::assembleEntitiesToDs)
                .toList();
        return new ResponseEntity<>(rendezVousDsList, HttpStatus.OK);
    }

    @Override
    public int countNumberOfRendezVousByDoctorAndDataRendezVous(Long matricule, Date dateRv) {
        return rendezVousService.countNumberOfRendezVousByDoctorAndDataRendezVous(matricule, dateRv);
    }
}
