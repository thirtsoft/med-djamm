package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.remote.model.HospitalisationDetailDs;
import com.meddjamm.sn.remote.model.HospitalisationDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/hospitalisation")
public interface HospitalisationApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HospitalisationDetailDs> creerHospitalisation(@RequestBody HospitalisationDs hospitalisationDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HospitalisationDetailDs> updateHospitalisation(@PathVariable Long id, @RequestBody HospitalisationDs hospitalisationDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HospitalisationDetailDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<HospitalisationDetailDs>> findAllHospitalisations();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteHospitalisation(@PathVariable Long id);
}
