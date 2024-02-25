package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.DossierPatientAssembler;
import com.meddjamm.sn.entity.DossierPatient;
import com.meddjamm.sn.remote.controller.api.DossierPatientApi;
import com.meddjamm.sn.remote.model.DossierPatientDetailDs;
import com.meddjamm.sn.remote.model.DossierPatientDs;
import com.meddjamm.sn.remote.model.DossierPatientListDs;
import com.meddjamm.sn.repository.DossierPatientRepository;
import com.meddjamm.sn.services.DossierPatientService;
import com.meddjamm.sn.utils.UtilString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class DossierPatientController implements DossierPatientApi {

    private final DossierPatientService dossierPatientService;

    private final DossierPatientAssembler dossierPatientAssembler;

    private final DossierPatientRepository dossierPatientRepository;

    public DossierPatientController(DossierPatientService dossierPatientService,
                                    DossierPatientAssembler dossierPatientAssembler,
                                    DossierPatientRepository dossierPatientRepository) {
        this.dossierPatientService = dossierPatientService;
        this.dossierPatientAssembler = dossierPatientAssembler;
        this.dossierPatientRepository = dossierPatientRepository;
    }

    @Override
    public void creerDossierPatient(DossierPatientDs dossierPatientDs) {
        DossierPatient dossierPatient = dossierPatientAssembler.assembleDossierPatientFromDs(dossierPatientDs);
        dossierPatientService.saveDossierPatient(dossierPatient);
    }

    @Override
    public void updateDossierPatient(Long id, DossierPatientDs dossierPatientDs) throws Exception {

    }

    @Override
    public ResponseEntity<DossierPatientDetailDs> findById(Long id) {
        return new ResponseEntity<>(dossierPatientAssembler.assemblesEntityToDs(
                dossierPatientService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DossierPatientDetailDs>> findDossierPatients() {
        return new ResponseEntity<>(dossierPatientAssembler.assembleEntitiesFrom(
                dossierPatientService.findAllDossierPatients()
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DossierPatientListDs>> findAllDossierPatients() {
        return new ResponseEntity<>(dossierPatientAssembler.createListeDossierPatient(
                dossierPatientService.findAllDossierPatients()
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DossierPatientDetailDs>> findDossierPatientsByPatient(String indexPatient) {
        return new ResponseEntity<>(dossierPatientAssembler.assembleEntitiesFrom(
                dossierPatientService.findDossierPatientsByPatient(indexPatient)
        ), HttpStatus.OK);
    }

    @Override
    public void deleteDossierPatient(Long id) {
        dossierPatientService.deleteDossierPatient(id);
    }

    public synchronized int createNumeroDossier(){
        int nbr =0;
        try{
            nbr = dossierPatientRepository.maxNumeroDossier();

        } catch (Exception e) {
        }
        return (nbr +1);
    }
}
