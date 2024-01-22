package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.remote.model.MaladieDs;
import com.meddjamm.sn.remote.model.RendezVousDetailDs;
import com.meddjamm.sn.remote.model.RendezVousDs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/rendez-vous")
public interface RendezVousApi {

    @PostMapping(value = "/save")
    ResponseEntity<RendezVousDetailDs> creerRendezVousDs(@RequestBody RendezVousDs rendezVousDs);

    @PutMapping(value = "/edit/{id}")
    ResponseEntity<RendezVousDetailDs> updateRendezVousDs(@PathVariable Long id, @RequestBody RendezVousDs rendezVousDs) throws Exception;

    @GetMapping(value = "/{id}")
    ResponseEntity<RendezVousDetailDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list")
    ResponseEntity<List<RendezVousDetailDs>> findAllRendezVous();

    @DeleteMapping(value = "/delete/{id}")
    void deleteRendezVous(@PathVariable Long id);
}
