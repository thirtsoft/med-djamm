package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.UtilisateurAssembler;
import com.meddjamm.sn.entity.Utilisateur;
import com.meddjamm.sn.remote.controller.api.UtilisateurApi;
import com.meddjamm.sn.remote.model.UtilisateurDs;
import com.meddjamm.sn.services.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200/")
public class UtilisateurController implements UtilisateurApi {

    private final UtilisateurService utilisateurService;
    private final UtilisateurAssembler utilisateurAssembler;

    public UtilisateurController(UtilisateurService utilisateurService,
                                 UtilisateurAssembler utilisateurAssembler) {
        this.utilisateurService = utilisateurService;
        this.utilisateurAssembler = utilisateurAssembler;
    }

    @Override
    public ResponseEntity<UtilisateurDs> creerUtilisateur(UtilisateurDs utilisateurDs) throws Exception {
        Utilisateur utilisateur = utilisateurAssembler.assembleUtilisateurFromDs(utilisateurDs);
        return new ResponseEntity<>(utilisateurAssembler.assembleUtilisateurDsFromEntity(
                utilisateurService.saveUtilisateur(utilisateur)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UtilisateurDs> updateUtilisateur(Long id, UtilisateurDs utilisateurDs) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<UtilisateurDs> findUtilisateurById(Long id) throws Exception {
        return new ResponseEntity<>(utilisateurAssembler.assembleUtilisateurDsFromEntity(
                utilisateurService.findUtilisateurById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UtilisateurDs>> findAllUtilisateurs() {
        return new ResponseEntity<>(utilisateurAssembler.assembleEntitiesFrom(
                utilisateurService.findAllUtilisateurs()
        ), HttpStatus.OK);
    }

    @Override
    public void deleteUtilisateur(Long id) {
    }
}
