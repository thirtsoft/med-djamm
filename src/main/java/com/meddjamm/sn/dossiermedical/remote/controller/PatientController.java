package com.meddjamm.sn.dossiermedical.remote.controller;

import com.meddjamm.sn.dossiermedical.assembler.CSVExportAssembler;
import com.meddjamm.sn.dossiermedical.assembler.PatientAssembler;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.remote.controller.api.PatientApi;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientMinDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientUpdateDs;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import com.meddjamm.sn.services.ReportPdfService;
import com.meddjamm.sn.utils.CSVSupport;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.TEXT_PLAIN;

@RestController
public class PatientController implements PatientApi {

    private final PatientService patientService;
    private final PatientAssembler patientAssembler;
    private final CSVExportAssembler csvExportAssembler;
    private final ReportPdfService reportPdfService;

    public PatientController(PatientService patientService, PatientAssembler patientAssembler, CSVExportAssembler csvExportAssembler, ReportPdfService reportPdfService) {
        this.patientService = patientService;
        this.patientAssembler = patientAssembler;
        this.csvExportAssembler = csvExportAssembler;
        this.reportPdfService = reportPdfService;
    }

    @Override
    public ResponseEntity<PatientMinDs> creerPatient(PatientDetailDs patientDetailDs) {
        Patient patientAjouter = patientAssembler.assemblePatientFromDs(patientDetailDs);
        return new ResponseEntity<>(patientAssembler.assembleMinFrom(patientService.savePatient(patientAjouter)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PatientDetailDs> creerDossierPatient(PatientDetailDs patientDetailDs) {
        Patient patientAjouter = patientAssembler.assemblePatientFromDs(patientDetailDs);
        return new ResponseEntity<>(patientAssembler.assemblePatientDetails(patientService.savePatient(patientAjouter)), HttpStatus.CREATED);
    }

    @Override
    public void updatePatientByMedecin(Long id, PatientUpdateDs patientUpdateDs) throws Exception {
        Patient patientModifier = patientAssembler.assembleUpdatePatient(patientUpdateDs);
        patientService.updatePatientByMedeccin(id, patientModifier);
    }

    @Override
    public ResponseEntity<PatientMinDs> updatePatientByAdministration(Long id, PatientDetailDs patientDetailDs) throws Exception {
        Patient patientAjouter = patientAssembler.assembleUpdatePatientFromDs(patientDetailDs);
        return new ResponseEntity<>(patientAssembler.assembleMinFrom(patientService.updatePatient(id, patientAjouter)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PatientDetailDs> findById(Long id) {
        PatientDetailDs patientResult = patientAssembler.assemblePatientDetails(patientService.findById(id));
        return new ResponseEntity<>(patientResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PatientDetailDs> findPatientByIndex(String index) {
        PatientDetailDs patientResult = patientAssembler.assemblePatientDetails(patientService.findByCode(index));
        return new ResponseEntity<>(patientResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PatientMinDs>> findAllPatients() {
        List<PatientMinDs> patientResult = patientAssembler.assembleEntitiesFrom(patientService.findAllPatients());
        return new ResponseEntity<>(patientResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PatientMinDs>> findAllPatients(Integer pageNumber, Integer pageSize) {
        List<PatientMinDs> patientResult = patientAssembler.assembleEntitiesFrom(patientService.findAllPatients(pageNumber, pageSize).getContent());
        return new ResponseEntity<>(patientResult, HttpStatus.OK);
    }

    @Override
    public void deletePatient(Long id) {
        patientService.deletePatient(id);
    }

    @Override
    public ResponseEntity<InputStreamResource> exportPatients() {
        List<List<String>> strings = patientAssembler.assembleEntitiesFrom(patientService.findAllPatients()).stream()
                .map(csvExportAssembler::mapEntityToList)
                .toList();
        return ResponseEntity.ok()
                .contentType(TEXT_PLAIN)
                .body(CSVSupport.generate(CSVExportAssembler.headers().toArray(new String[]{}), strings));
    }

    @Override
    public void exportPatientsToPDF(HttpServletResponse response) throws IOException {
        reportPdfService.exportToPDF(response, patientAssembler.assembleEntitiesFrom(patientService.findAllPatients()));
    }

    @GetMapping(value = "/mySession")
    Authentication authentication(Authentication authentication) {
        return authentication;
    }

}
