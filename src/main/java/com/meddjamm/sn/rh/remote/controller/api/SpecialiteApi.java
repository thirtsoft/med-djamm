package com.meddjamm.sn.rh.remote.controller.api;

import com.meddjamm.sn.rh.remote.model.SpecialiteDs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/specialite")
public interface SpecialiteApi {

    @PostMapping(value = "/save")
    ResponseEntity<?> creerSpeciality(@RequestBody SpecialiteDs specialiteDs);

    @PutMapping("/edit/{id}")
    void updateSpeciality(@PathVariable Long id, @RequestBody SpecialiteDs specialiteDs);

    @GetMapping(value = "/{id}")
    ResponseEntity<SpecialiteDs> findSpecialityById(@PathVariable Long id);

    @GetMapping(value = "/list")
    ResponseEntity<List<SpecialiteDs>> getAllSpecialities();

    @DeleteMapping(value = "/delete/{id}")
    void deleteSpeciality(@PathVariable Long id);


}
