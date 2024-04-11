package com.meddjamm.sn.dossiermedical.remote.controller.api;

import com.meddjamm.sn.dossiermedical.remote.model.HospitalisationDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.HospitalisationDs;
import com.meddjamm.sn.dossiermedical.remote.model.HospitalisationListDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    ResponseEntity<List<HospitalisationListDs>> findAllHospitalisations();

    @GetMapping(value = "/patient/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<HospitalisationListDs>> getHospitalisationListByPatient(@PathVariable("code") String code);

    @GetMapping(value = "/detail/patient/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<HospitalisationDetailDs>> getHospitalisationsDetailsByPatient(@PathVariable("code") String code);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteHospitalisation(@PathVariable Long id);
}
