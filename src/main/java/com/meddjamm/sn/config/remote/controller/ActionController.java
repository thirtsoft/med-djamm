package com.meddjamm.sn.config.remote.controller;

import com.meddjamm.sn.config.assembler.ActionAssembler;
import com.meddjamm.sn.config.entity.Action;
import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.remote.controller.api.ActionApi;
import com.meddjamm.sn.config.remote.model.ActionListDs;
import com.meddjamm.sn.config.service.ActionService;
import com.meddjamm.sn.config.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
public class ActionController implements ActionApi {

    private final ActionService actionService;
    private final ActionAssembler actionAssembler;

    private final UtilisateurService utilisateurService;

    @Override
    public void creerAction(ActionListDs actionListDs) throws Exception {
        actionService.saveAction(actionAssembler.assembleActionFromDs(actionListDs));
    }

    @Override
    public void updateAction(Long id, ActionListDs actionListDs) {
        Action action = actionAssembler.assembleActionFromDs(actionListDs);
        actionService.updateAction(id, action);
    }

    @Override
    public ResponseEntity<ActionListDs> findById(Long id) {
        return new ResponseEntity<>(actionAssembler.assembleEntityToDs(actionService.findById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ActionListDs> findByCode(String code) {
        return new ResponseEntity<>(actionAssembler.assembleEntityToDs(actionService.findByCode(code)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ActionListDs> findByLibelle(String libelle) {
        return new ResponseEntity<>(actionAssembler.assembleEntityToDs(actionService.findByLibelle(libelle)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ActionListDs> findByTypeProfil(Long typeProfil) {
        return null;
    }

    @Override
    public ResponseEntity<ActionListDs> findByLibelleAndProfil(String libelle, Long typeProfil) {
        return new ResponseEntity<>(actionAssembler.assembleEntityToDs(actionService.findByLibelle(libelle, typeProfil)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ActionListDs>> findAllActions() {
        return new ResponseEntity<>(actionAssembler.assembleEntitiesFrom(
                actionService.findAll()
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ActionListDs>> findAllActionsByTypeProfil(Long typeProfil) {
        return new ResponseEntity<>(actionAssembler.assembleEntitiesFrom(
                actionService.findByTypeProfil(typeProfil)
        ), HttpStatus.OK);
    }

    @Override
    public void deleteAction(Long id) {
        actionService.deleteAction(id);
    }

    @Override
    public ResponseEntity<List<ActionListDs>> canDo(Long userId) {
        Utilisateur utilisateur = utilisateurService.findUtilisateurById(userId);
        return new ResponseEntity<>(actionAssembler.assembleEntitiesFrom(
                actionService.getListActionByProfil(utilisateur.getProfil().getCode())
        ), HttpStatus.OK);
    }
}
