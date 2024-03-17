package com.meddjamm.sn.dossiermedical.remote.controller;

import com.meddjamm.sn.dossiermedical.assembler.ConsultationAssembler;
import com.meddjamm.sn.dossiermedical.remote.controller.api.ConsultationApi;
import com.meddjamm.sn.dossiermedical.remote.model.ConsultationDs;
import com.meddjamm.sn.dossiermedical.services.ConsultationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsultationController implements ConsultationApi {

    private final ConsultationService consultationService;

    private final ConsultationAssembler consultationAssembler;

    public ConsultationController(ConsultationService consultationService,
                                  ConsultationAssembler consultationAssembler) {
        this.consultationService = consultationService;
        this.consultationAssembler = consultationAssembler;
    }

    @Override
    public ResponseEntity<ConsultationDs> creerConsultation(ConsultationDs consultationDs) {
        return new ResponseEntity<>(consultationAssembler.assembleEntityToDs(
                consultationService.saveConsultation(
                        consultationAssembler.assembleConsultationFromDs(consultationDs)
                )
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ConsultationDs> updateConsultation(Long id, ConsultationDs consultationDs) throws Exception {
        return new ResponseEntity<>(consultationAssembler.assembleEntityToDs(
                consultationService.updateConsultation(id,
                        consultationAssembler.assembleConsultationFromDs(consultationDs)
                )
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ConsultationDs> findById(Long id) {
        return new ResponseEntity<>(consultationAssembler.assembleEntityToDs(
                consultationService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ConsultationDs>> findAllConsultations() {
        return new ResponseEntity<>(consultationAssembler.assembleEntitiesFrom(
                consultationService.findAllConsultations()
        ), HttpStatus.OK);
    }

    @Override
    public void deleteConsultation(Long id) {
        consultationService.deleteConsultation(id);
    }

    @Override
    public ResponseEntity<List<ConsultationDs>> findAllConsultationsByPatientId(String code) {
        return new ResponseEntity<>(consultationAssembler.assembleEntitiesFrom(
                consultationService.findConsultationsByPatientId(code)
        ), HttpStatus.OK);
    }
}
