package com.meddjamm.sn.config.remote.controller.api;

import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import com.meddjamm.sn.config.remote.model.ValidationRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/utilisateur")
public interface UtilisateurApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDs> creerUtilisateur(@RequestBody @Valid UtilisateurDs utilisateurDs) throws Exception;

    @PostMapping(value = "/activation")
    ResponseEntity<Void> activation(@RequestBody @Valid ValidationRequest activation);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDs> updateUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDs utilisateurDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDs> findUtilisateurById(@PathVariable Long id) throws Exception;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UtilisateurDs>> findAllUtilisateurs();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteUtilisateur(@PathVariable Long id);

    @GetMapping(value = "/monprofil/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDs> findUtilisateurProfil(@PathVariable Long id) throws Exception;

}
