package com.meddjamm.sn.rh.remote.controller;

import com.meddjamm.sn.rh.assembler.ProfilAssembler;
import com.meddjamm.sn.rh.entity.Profil;
import com.meddjamm.sn.rh.remote.controller.api.ProfilApi;
import com.meddjamm.sn.rh.remote.model.ProfilDs;
import com.meddjamm.sn.rh.services.ProfilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ProfilController implements ProfilApi {

    private final ProfilService profilService;
    private final ProfilAssembler profilAssembler;

    public ProfilController(ProfilService profilService,
                            ProfilAssembler profilAssembler) {
        this.profilService = profilService;
        this.profilAssembler = profilAssembler;
    }

    @Override
    public ResponseEntity<ProfilDs> creerProfil(ProfilDs profilDs) {
        Profil profil = profilAssembler.assembleProfilFromDs(profilDs);
        return new ResponseEntity<>(profilAssembler.assembleEntityToDs(
                profilService.saveProfil(profil)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProfilDs> updateProfil(Long id, ProfilDs profilDs) throws Exception {
        Profil profil = profilAssembler.assembleProfilFromDs(profilDs);
        return new ResponseEntity<>(profilAssembler.assembleEntityToDs(
                profilService.updateProfil(id, profil)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProfilDs> findProfilById(Long id) {
        return new ResponseEntity<>(profilAssembler.assembleEntityToDs(
                profilService.findProfilById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProfilDs> findByCode(String code) {
        return new ResponseEntity<>(profilAssembler.assembleEntityToDs(
                profilService.findByCode(code)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProfilDs> findByLibelle(String libelle) {
        return new ResponseEntity<>(profilAssembler.assembleEntityToDs(
                profilService.findByLibelle(libelle)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProfilDs> findByCodeFromAction(String code) {
        return new ResponseEntity<>(profilAssembler.assembleEntityToDs(
                profilService.findByCodeFromAction(code)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProfilDs>> findAllProfils() {
        return new ResponseEntity<>(profilAssembler.assembleEntitiesFrom(
                profilService.findAllActive()
        ), HttpStatus.OK);
    }

    @Override
    public void deleteProfil(Long id) {
        profilService.deleteProfil(id);
    }
}