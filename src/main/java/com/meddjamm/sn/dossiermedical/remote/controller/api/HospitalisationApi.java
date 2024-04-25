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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/hospitalisation")
public interface HospitalisationApi {

    @PostMapping(value = "/save")
    void creerHospitalisation(@RequestBody HospitalisationDs hospitalisationDs);

    @PutMapping(value = "/edit/{id}")
    void updateHospitalisation(@PathVariable Long id, @RequestBody HospitalisationDs hospitalisationDs) throws Exception;

    @GetMapping(value = "/{id}")
    ResponseEntity<HospitalisationDetailDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<HospitalisationListDs>> findAllHospitalisations();

    @GetMapping(value = "/patient/{code}")
    ResponseEntity<List<HospitalisationListDs>> getHospitalisationListByPatient(@PathVariable("code") String code);

    @GetMapping(value = "/detail/patient/{code}")
    ResponseEntity<List<HospitalisationDetailDs>> getHospitalisationsDetailsByPatient(@PathVariable("code") String code);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteHospitalisation(@PathVariable Long id);

    @PutMapping("/exam-complementaire/{hospitalisationId}/add-hospitalisation-biologic-file")
    boolean addExamBiologicToHospitalisation(@PathVariable Long hospitalisationId, @RequestParam(required = false) MultipartFile biologic) throws Exception;

    @PutMapping("/exam-complementaire/{hospitalisationId}/add-hospitalisation-immunologic-file")
    boolean addExamImmunologicToHospitalisation(@PathVariable Long hospitalisationId, @RequestParam(required = false) MultipartFile immunologic) throws Exception;

    @PutMapping("/exam-complementaire/{hospitalisationId}/add-hospitalisation-imager-file")
    boolean addExamImagerToHospitalisation(@PathVariable Long hospitalisationId, @RequestParam(required = false) MultipartFile imager) throws Exception;

    @PutMapping("/exam-complementaire/{hospitalisationId}/add-hospitalisation-hematologic-file")
    boolean addExamHematologicToHospitalisation(@PathVariable Long hospitalisationId, @RequestParam(required = false) MultipartFile hematologic) throws Exception;

}
