package com.meddjamm.sn.dossiermedical.remote.controller.api;

import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientMinDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientUpdateDs;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/patient")
public interface PatientApi {

    @PostMapping(value = "/save")
    ResponseEntity<PatientMinDs> creerPatient(@RequestBody PatientDetailDs patientDetailDs);

    @PostMapping(value = "/generated-dossier-patient")
    ResponseEntity<PatientDetailDs> creerDossierPatient(@RequestBody PatientDetailDs patientDetailDs);

    @PatchMapping(value = "/edit/{id}")
    void updatePatientByMedecin(@PathVariable Long id, @RequestBody PatientUpdateDs patientUpdateDs) throws Exception;

    @PatchMapping(value = "/edit/by-administration/{id}")
    ResponseEntity<PatientMinDs> updatePatientByAdministration(@PathVariable Long id, @RequestBody PatientDetailDs patientDetailDs) throws Exception;

    @GetMapping(value = "/{id}")
    ResponseEntity<PatientDetailDs> findById(@PathVariable Long id);

    @GetMapping(value = "/detail/{index}")
    ResponseEntity<PatientDetailDs> findPatientByIndex(@PathVariable String index);

    @GetMapping(value = "/list")
    ResponseEntity<List<PatientMinDs>> findAllPatients();

    @GetMapping(value = "/list/{pageNumber}/{pageSize}")
    ResponseEntity<List<PatientMinDs>> findAllPatients(@PathVariable Integer pageNumber, @PathVariable Integer pageSize);

    @DeleteMapping(value = "/delete/{id}")
    void deletePatient(@PathVariable Long id);

    @GetMapping(value = "/export-csv")
    @Transactional(readOnly = true)
    ResponseEntity<InputStreamResource> exportPatients();

    @GetMapping(value = "/export-pdf")
    @Transactional(readOnly = true)
    void exportPatientsToPDF(HttpServletResponse response) throws IOException;
}