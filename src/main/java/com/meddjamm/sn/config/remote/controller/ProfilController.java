package com.meddjamm.sn.config.remote.controller;

import com.meddjamm.sn.config.assembler.ProfilAssembler;
import com.meddjamm.sn.config.entity.Profil;
import com.meddjamm.sn.config.remote.controller.api.ProfilApi;
import com.meddjamm.sn.config.remote.model.ProfilDs;
import com.meddjamm.sn.config.service.ProfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Transactional
@RequiredArgsConstructor
public class ProfilController implements ProfilApi {

    private final ProfilService profilService;
    private final ProfilAssembler profilAssembler;

    @Override
    public ResponseEntity<ProfilDs> creerProfil(ProfilDs profilDs) throws Exception {
        Profil profil = profilAssembler.assembleProfilFromDs(profilDs);
        return new ResponseEntity<>(profilAssembler.assembleEntityToDs(profilService.saveProfil(profil)), CREATED);
    }

    @Override
    public ResponseEntity<ProfilDs> updateProfil(Long id, ProfilDs profilDs) throws Exception {
        Profil profil = profilAssembler.assembleProfilFromDs(profilDs);
        return new ResponseEntity<>(profilAssembler.assembleEntityToDs(
                profilService.updateProfil(id, profil)
        ), OK);
    }

    @Override
    public ResponseEntity<ProfilDs> findProfilById(Long id) {
        return new ResponseEntity<>(profilAssembler.assembleEntityToDs(
                profilService.findProfilById(id)
        ), OK);
    }

    @Override
    public ResponseEntity<ProfilDs> findByCode(String code) {
        return new ResponseEntity<>(profilAssembler.assembleEntityToDs(
                profilService.findByCode(code)
        ), OK);
    }

    @Override
    public ResponseEntity<ProfilDs> findByLibelle(String libelle) {
        return new ResponseEntity<>(profilAssembler.assembleEntityToDs(
                profilService.findByLibelle(libelle)), OK);
    }

    @Override
    public ResponseEntity<ProfilDs> findByCodeFromAction(String code) {
        return new ResponseEntity<>(profilAssembler.assembleEntityToDs(
                profilService.findByCodeFromAction(code)
        ), OK);
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
