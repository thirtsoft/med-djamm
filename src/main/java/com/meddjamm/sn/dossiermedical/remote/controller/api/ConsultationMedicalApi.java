package com.meddjamm.sn.dossiermedical.remote.controller.api;

import com.meddjamm.sn.dossiermedical.remote.model.ConsultationMedicalDs;
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

@RequestMapping(value = APP_ROOT + "/consultationmedical")
public interface ConsultationMedicalApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    Long creerConsultationMedical(@RequestBody ConsultationMedicalDs consultationMedicalDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Long updateConsultationMedical(@PathVariable Long id, @RequestBody ConsultationMedicalDs consultationMedicalDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ConsultationMedicalDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ConsultationMedicalDs>> findAllConsultationMedicals();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteConsultationMedical(@PathVariable Long id);

    @GetMapping(value = "/by-patient/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ConsultationMedicalDs>> findAllConsultationMedicalsByPatientId(@PathVariable String code);

    @PutMapping("/exam-biologic/{consultationId}/add-consultation-biologic-file")
    boolean addConsultationBiologicToConsultation(@PathVariable Long consultationId, @RequestParam(required = false) MultipartFile biologic) throws Exception;

    @PutMapping("/exam-biologic/{consultationId}/add-consultation-immunologic-file")
    boolean addConsultationImmunologicToConsultation(@PathVariable Long consultationId, @RequestParam(required = false) MultipartFile immunologic) throws Exception;

    @PutMapping("/exam-biologic/{consultationId}/add-consultation-imager-file")
    boolean addConsultationImagerToConsultation(@PathVariable Long consultationId, @RequestParam(required = false) MultipartFile imager) throws Exception;

    @PutMapping("/exam-biologic/{consultationId}/add-consultation-hematologic-file")
    boolean addConsultationHematologicToConsultation(@PathVariable Long consultationId, @RequestParam(required = false) MultipartFile hematologic) throws Exception;

    @GetMapping(value = "/by-circuit/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ConsultationMedicalDs>> findAllConsultationMedicalsByCircuitId(@PathVariable Long code);
}
