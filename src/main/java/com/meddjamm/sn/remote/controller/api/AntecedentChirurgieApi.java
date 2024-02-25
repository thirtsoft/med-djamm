package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.remote.model.AntecedentChirurgieDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/antecedent-chirurgie")
public interface AntecedentChirurgieApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    void creerAntecedentChirurgie(@RequestBody AntecedentChirurgieDs antecedentChirurgieDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateAntecedentChirurgie(@PathVariable Long id, @RequestBody AntecedentChirurgieDs antecedentChirurgieDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AntecedentChirurgieDs> findById(@PathVariable Long id);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteAntecedentChirurgie(@PathVariable Long id);
}
