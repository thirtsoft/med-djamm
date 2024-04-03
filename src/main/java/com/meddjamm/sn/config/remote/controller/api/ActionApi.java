package com.meddjamm.sn.config.remote.controller.api;

import com.meddjamm.sn.config.remote.model.ActionListDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/action")
public interface ActionApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    void creerAction(@RequestBody ActionListDs actionListDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateAction(@PathVariable Long id, @RequestBody ActionListDs actionListDs);

    @GetMapping(value = "/byid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ActionListDs> findById(@PathVariable Long id);

    @GetMapping(value = "/by-code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ActionListDs> findByCode(@PathVariable String code);

    @GetMapping(value = "/by-libelle/{libelle}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ActionListDs> findByLibelle(@PathVariable String libelle);

    @GetMapping(value = "/by-profil/{typeProfil}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ActionListDs> findByTypeProfil(@PathVariable Long typeProfil);

    @GetMapping(value = "/{libelle}/{typeProfil}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ActionListDs> findByLibelleAndProfil(
            @PathVariable String libelle, @PathVariable Long typeProfil);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ActionListDs>> findAllActions();

    @GetMapping(value = "/list/{typeProfil}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ActionListDs>> findAllActionsByTypeProfil(@PathVariable Long typeProfil);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteAction(@PathVariable Long id);

    @GetMapping(value = "/users/{userId}/actions")
    ResponseEntity<List<ActionListDs>> canDo(@PathVariable Long userId);
}
