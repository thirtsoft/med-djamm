package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.remote.model.UtilisateurDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/utilisateur")
public interface UtilisateurApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDs> creerUtilisateur(@RequestBody UtilisateurDs utilisateurDs) throws Exception;

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDs> updateUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDs utilisateurDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDs> findUtilisateurById(@PathVariable Long id) throws Exception;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UtilisateurDs>> findAllUtilisateurs();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteUtilisateur(@PathVariable Long id);

}
