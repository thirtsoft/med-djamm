package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.remote.model.OrdonnanceDetailDs;
import com.meddjamm.sn.remote.model.OrdonnanceDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/ordonnance")
public interface OrdonnanceApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrdonnanceDetailDs> creerOrdonnance(@RequestBody OrdonnanceDs ordonnanceDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrdonnanceDetailDs> updateOrdonnance(@PathVariable Long id, @RequestBody OrdonnanceDs ordonnanceDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrdonnanceDetailDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrdonnanceDetailDs>> findAllOrdonnances();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteOrdonnance(@PathVariable Long id);
}
