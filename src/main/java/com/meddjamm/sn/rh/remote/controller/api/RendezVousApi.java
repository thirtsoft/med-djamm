package com.meddjamm.sn.rh.remote.controller.api;

import com.meddjamm.sn.rh.remote.model.RendezVousDeplaceDs;
import com.meddjamm.sn.rh.remote.model.RendezVousDetailDs;
import com.meddjamm.sn.rh.remote.model.RendezVousDs;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/rendezvous")
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

    @GetMapping(value = "/by-doctor/{matricule}")
    ResponseEntity<List<RendezVousDetailDs>> findByDoctorId(@PathVariable Long matricule);

    @GetMapping(value = "/treelatest/by-patient/{patientId}")
    ResponseEntity<List<RendezVousDetailDs>> findTreeLatestRendezVousByPatient(@PathVariable Long patientId);

    @GetMapping(value = "/du-jour")
    ResponseEntity<List<RendezVousDetailDs>> getAllRendezVousInDay();

    @GetMapping(value = "/by-doctor/in-month/{matricule}")
    ResponseEntity<List<RendezVousDetailDs>> findAllRendezVousOfDoctorInMonth(@PathVariable Long matricule);

    @PostMapping(value = "/deplacez-rendezvous")
    void DeplacerRendezVous(@RequestBody RendezVousDeplaceDs rendezVousDeplace);

    @GetMapping(value = "/by-date/{dateRv}")
    ResponseEntity<List<RendezVousDetailDs>> findRendezVousBySelectedDate(@PathVariable
                                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateRv);

    @GetMapping(value = "/by-doctor/{matricule}/by-date/{dateRv}")
    int countNumberOfRendezVousByDoctorAndDataRendezVous(@PathVariable Long matricule,
                                                         @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateRv);


}
