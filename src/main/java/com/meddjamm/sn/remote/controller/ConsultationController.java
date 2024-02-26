package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.ConsultationAssembler;
import com.meddjamm.sn.entity.Consultation;
import com.meddjamm.sn.remote.controller.api.ConsultationApi;
import com.meddjamm.sn.remote.model.ConsultationDetailDs;
import com.meddjamm.sn.remote.model.ConsultationDs;
import com.meddjamm.sn.services.ConsultationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class ConsultationController implements ConsultationApi {

    private final ConsultationService consultationService;

    private final ConsultationAssembler consultationAssembler;

    public ConsultationController(ConsultationService consultationService,
                                  ConsultationAssembler consultationAssembler) {
        this.consultationService = consultationService;
        this.consultationAssembler = consultationAssembler;
    }

    @Override
    public ResponseEntity<ConsultationDetailDs> creerConsultation(ConsultationDs consultationDs) {
        Consultation consultation = consultationAssembler.assembleConsultationFromDs(consultationDs);
        consultationService.saveConsultation(consultation);
        return new ResponseEntity<>(consultationAssembler.assembleConsultationToDs(consultation), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ConsultationDetailDs> updateConsultation(Long id, ConsultationDs consultationDs) throws Exception {
        Consultation consultation = consultationAssembler.assembleConsultationFromDs(consultationDs);
        consultationService.updateConsultation(id,consultation);
        return new ResponseEntity<>(consultationAssembler.assembleConsultationToDs(consultation), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ConsultationDetailDs> findById(Long id) {
        return new ResponseEntity<>(consultationAssembler.assembleConsultationToDs(
                consultationService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ConsultationDetailDs>> findAllConsultations() {
        return new ResponseEntity<>(consultationAssembler
                .assembleEntitiesFrom(consultationService.findAllConsultations()), HttpStatus.OK);
    }

    @Override
    public void deleteConsultation(Long id) {
        consultationService.deleteConsultation(id);
    }
}
