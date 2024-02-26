package com.meddjamm.sn.rh.remote.controller;

import com.meddjamm.sn.rh.assembler.ActionAssembler;
import com.meddjamm.sn.rh.entity.Action;
import com.meddjamm.sn.rh.remote.controller.api.ActionApi;
import com.meddjamm.sn.rh.remote.model.ActionListDs;
import com.meddjamm.sn.rh.services.ActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ActionController implements ActionApi {

    private final ActionService actionService;

    private final ActionAssembler actionAssembler;

    public ActionController(ActionService actionService, ActionAssembler actionAssembler) {
        this.actionService = actionService;
        this.actionAssembler = actionAssembler;
    }

    @Override
    public void creerAction(ActionListDs actionListDs) {
        Action action = actionAssembler.assembleActionFromDs(actionListDs);
        actionService.saveAction(action);
    }

    @Override
    public void updateAction(Long id, ActionListDs actionListDs) throws Exception {
        Action action = actionAssembler.assembleActionFromDs(actionListDs);
        actionService.updateAction(id, action);
    }

    @Override
    public ResponseEntity<ActionListDs> findById(Long id) {
        return new ResponseEntity<>(actionAssembler.assembleEntityToDs(
                actionService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ActionListDs> findByCode(String code) {
        return new ResponseEntity<>(actionAssembler.assembleEntityToDs(
                actionService.findByCode(code)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ActionListDs> findByLibelle(String libelle) {
        return new ResponseEntity<>(actionAssembler.assembleEntityToDs(
                actionService.findByLibelle(libelle)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ActionListDs> findByTypeProfil(Long typeProfil) {
        return null;
    }

    @Override
    public ResponseEntity<ActionListDs> findByLibelleAndProfil(String libelle, Long typeProfil) {
        return new ResponseEntity<>(actionAssembler.assembleEntityToDs(
                actionService.findByLibelle(libelle, typeProfil)
        ), HttpStatus.OK);
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
}
