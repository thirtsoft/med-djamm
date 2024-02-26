package com.meddjamm.sn.rh.remote.controller.api;

import com.meddjamm.sn.rh.remote.model.ProfilDs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/profil")
public interface ProfilApi {

    @PostMapping(value = "/save")
    ResponseEntity<ProfilDs> creerProfil(@RequestBody ProfilDs profilDs);

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