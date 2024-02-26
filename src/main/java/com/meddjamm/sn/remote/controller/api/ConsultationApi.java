package com.meddjamm.sn.remote.controller.api;

import com.meddjamm.sn.remote.model.ConsultationDetailDs;
import com.meddjamm.sn.remote.model.ConsultationDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/consultation")
public interface ConsultationApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ConsultationDetailDs> creerConsultation(@RequestBody ConsultationDs consultationDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ConsultationDetailDs> updateConsultation(@PathVariable Long id, @RequestBody ConsultationDs consultationDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ConsultationDetailDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ConsultationDetailDs>> findAllConsultations();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteConsultation(@PathVariable Long id);
}
