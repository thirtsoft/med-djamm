package com.meddjamm.sn.config.remote.controller.api;

import com.meddjamm.sn.config.remote.model.ProfilDs;
import com.meddjamm.sn.utils.ResponseMassageDs;
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

@RequestMapping(value = APP_ROOT + "/profil")
public interface ProfilApi {

    @PostMapping(value = "/save")
    ResponseMassageDs creerProfil(@RequestBody ProfilDs profilDs);

    @PutMapping(value = "/edit/{id}")
    ResponseEntity<ProfilDs> updateProfil(@PathVariable Long id, @RequestBody ProfilDs profilDs) throws Exception;

    @GetMapping(value = "/{id}")
    ResponseEntity<ProfilDs> findProfilById(@PathVariable Long id);

    @GetMapping(value = "/by-code/{code}")
    ResponseEntity<ProfilDs> findByCode(@PathVariable String code);

    @GetMapping(value = "/by-libelle/{libelle}")
    ResponseEntity<ProfilDs> findByLibelle(@PathVariable String libelle);

    @GetMapping(value = "/fromcode/{code}")
    ResponseEntity<ProfilDs> findByCodeFromAction(@PathVariable String code);

    @GetMapping(value = "/list")
    ResponseEntity<List<ProfilDs>> findAllProfils();

    @DeleteMapping(value = "/delete/{id}")
    void deleteProfil(@PathVariable Long id);
}