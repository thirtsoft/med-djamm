package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.remote.model.MedecinDetailDs;
import com.meddjamm.sn.config.remote.model.MedecinDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequestMapping(value = APP_ROOT + "/medecin")
public interface MedecinApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MedecinDs> creerMedecin(@RequestBody MedecinDs medecinDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MedecinDs> updateMedecin(@PathVariable Long id, @RequestBody MedecinDs medecinDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MedecinDetailDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<MedecinDetailDs>> findAllMedecins();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteMedecin(@PathVariable Long id);
}
