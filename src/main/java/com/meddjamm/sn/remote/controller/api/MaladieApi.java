package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.entity.Maladie;
import com.meddjamm.sn.remote.model.MaladieDs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/maladie")
public interface MaladieApi {

    @PostMapping(value = "/save")
    ResponseEntity<Maladie> creerMaladie(@RequestBody Maladie maladie);

    @PutMapping(value = "/edit/{id}")
    ResponseEntity<Maladie> updateMaladie(@PathVariable Long id, @RequestBody Maladie maladie) throws Exception;

    @GetMapping(value = "/{id}")
    ResponseEntity<MaladieDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list")
    ResponseEntity<List<MaladieDs>> findAllMaladies();

    @DeleteMapping(value = "/delete/{id}")
    void deleteMaladie(@PathVariable Long id);
}
