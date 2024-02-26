package com.meddjamm.sn.dossiermedical.remote.controller.api;

import com.meddjamm.sn.dossiermedical.remote.model.ExamenComplementaireDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.ExamenComplementaireDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/examencomplementaire")
public interface ExamenComplementaireApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ExamenComplementaireDetailDs> creerExamenComplementaire(@RequestBody ExamenComplementaireDs examenComplementaireDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ExamenComplementaireDetailDs> updateExamenComplementaire(@PathVariable Long id, @RequestBody ExamenComplementaireDs examenComplementaireDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ExamenComplementaireDetailDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ExamenComplementaireDetailDs>> findAllExamenComplementaires();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteExamenComplementaire(@PathVariable Long id);

    @GetMapping(value = "/by-patient/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ExamenComplementaireDetailDs>> findAllExamenComplementairesByPatientId(@PathVariable String code);
}
