package com.meddjamm.sn.dossiermedical.remote.controller;

import com.meddjamm.sn.dossiermedical.assembler.ConsultationMedicalAssembler;
import com.meddjamm.sn.dossiermedical.remote.controller.api.ConsultationMedicalApi;
import com.meddjamm.sn.dossiermedical.remote.model.ConsultationMedicalDs;
import com.meddjamm.sn.dossiermedical.services.ConsultationMedicalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ConsultationMedicalController implements ConsultationMedicalApi {

    private final ConsultationMedicalService consultationMedicalService;

    private final ConsultationMedicalAssembler consultationMedicalAssembler;

    public ConsultationMedicalController(ConsultationMedicalService consultationMedicalService,
                                         ConsultationMedicalAssembler consultationMedicalAssembler) {
        this.consultationMedicalService = consultationMedicalService;
        this.consultationMedicalAssembler = consultationMedicalAssembler;
    }

    @Override
    public ResponseEntity<ConsultationMedicalDs> creerConsultationMedical(ConsultationMedicalDs consultationMedicalDs) {
        return new ResponseEntity<>(consultationMedicalAssembler.assembleEntityToDs(
                consultationMedicalService.saveConsultationMedical(
                        consultationMedicalAssembler.assembleConsultationMedicallisteFromDs(consultationMedicalDs)
                )
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ConsultationMedicalDs> updateConsultationMedical(Long id, ConsultationMedicalDs consultationMedicalDs) throws Exception {
        return new ResponseEntity<>(consultationMedicalAssembler.assembleEntityToDs(
                consultationMedicalService.updateConsultationMedical(id,
                        consultationMedicalAssembler.assembleConsultationMedicallisteFromDs(consultationMedicalDs)
                )
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ConsultationMedicalDs> findById(Long id) {
        return new ResponseEntity<>(consultationMedicalAssembler.assembleEntityToDs(
                consultationMedicalService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ConsultationMedicalDs>> findAllConsultationMedicals() {
        return new ResponseEntity<>(consultationMedicalAssembler.assembleEntitiesFrom(
                consultationMedicalService.findAllConsultationMedicals()
        ), HttpStatus.OK);
    }

    @Override
    public void deleteConsultationMedical(Long id) {
        consultationMedicalService.deleteConsultationMedical(id);
    }

    @Override
    public ResponseEntity<List<ConsultationMedicalDs>> findAllConsultationMedicalsByPatientId(String code) {
        return new ResponseEntity<>(consultationMedicalAssembler.assembleEntitiesFrom(
                consultationMedicalService.findConsultationMedicalByPatientId(code)
        ), HttpStatus.OK);
    }

    @Override
    public boolean addConsultationBiologicToConsultation(Long consultationId, MultipartFile biologic) throws Exception {
        return consultationMedicalService.addConsultationBiologicToConsultation(consultationId, biologic);
    }

    @Override
    public boolean addConsultationImmunologicToConsultation(Long consultationId, MultipartFile immunologic) throws Exception {
        return consultationMedicalService.addConsultationImmunologicToConsultation(consultationId, immunologic);
    }

    @Override
    public boolean addConsultationImagerToConsultation(Long consultationId, MultipartFile imager) throws Exception {
        return consultationMedicalService.addConsultationImagerToConsultation(consultationId, imager);
    }

    @Override
    public boolean addConsultationHematologicToConsultation(Long consultationId, MultipartFile hematologic) throws Exception {
        return consultationMedicalService.addConsultationHematologicToConsultation(consultationId, hematologic);
    }

    @Override
    public ResponseEntity<List<ConsultationMedicalDs>> findAllConsultationMedicalsByCircuitId(Long code) {
        return new ResponseEntity<>(consultationMedicalAssembler.assembleEntitiesFrom(
                consultationMedicalService.findConsultationMedicalByCircuitId(code)
        ), HttpStatus.OK);
    }
}
