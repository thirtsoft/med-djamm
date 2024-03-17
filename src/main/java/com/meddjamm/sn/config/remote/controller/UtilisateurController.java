package com.meddjamm.sn.config.remote.controller;

import com.meddjamm.sn.config.assembler.UtilisateurAssembler;
import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.remote.controller.api.UtilisateurApi;
import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import com.meddjamm.sn.config.remote.model.ValidationRequest;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.config.service.impl.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin(value = "http://localhost:4200/")
@Transactional
@AllArgsConstructor
public class UtilisateurController implements UtilisateurApi {

    private final UtilisateurService utilisateurService;
    private final UtilisateurAssembler utilisateurAssembler;
    private final ValidationService validationService;

    @Override
    public ResponseEntity<UtilisateurDs> creerUtilisateur(UtilisateurDs utilisateurDs) throws Exception {
        Utilisateur utilisateur = utilisateurAssembler.assembleUtilisateurFromDs(utilisateurDs);
        return new ResponseEntity<>(utilisateurAssembler
                .assembleUtilisateurDsFromEntity(utilisateurService.saveUtilisateur(utilisateur)), CREATED);
    }

    @Override
    public ResponseEntity<Void> activation(@RequestBody @Valid ValidationRequest activation) {
        utilisateurService.lireEnFonctionDuCode(activation.code());
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<UtilisateurDs> updateUtilisateur(Long id, UtilisateurDs utilisateurDs) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<UtilisateurDs> findUtilisateurById(Long id) throws Exception {
        return new ResponseEntity<>(utilisateurAssembler
                .assembleUtilisateurDsFromEntity(utilisateurService.findUtilisateurById(id)), OK);
    }

    @Override
    public ResponseEntity<List<UtilisateurDs>> findAllUtilisateurs() {
        return new ResponseEntity<>(utilisateurAssembler
                .assembleEntitiesFrom(utilisateurService.findAllUtilisateurs()), OK);
    }

    @Override
    public void deleteUtilisateur(Long id) {
    }

    @Override
    public ResponseEntity<UtilisateurDs> findUtilisateurProfil(Long id) throws Exception {
        return new ResponseEntity<>(utilisateurAssembler
                .assembleUtilisateurDsFromEntity(utilisateurService.findUserById(id)), OK);
    }
}
