package com.meddjamm.sn.remote.controller;

import com.meddjamm.sn.assembler.ClassificationAssembler;
import com.meddjamm.sn.entity.Classification;
import com.meddjamm.sn.remote.controller.api.ClassificationApi;
import com.meddjamm.sn.remote.model.ClassificationDs;
import com.meddjamm.sn.remote.model.PatientMinDs;
import com.meddjamm.sn.services.ClassificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ClassificationController implements ClassificationApi {

    private final ClassificationService classificationService;
    private final ClassificationAssembler classificationAssembler;

    public ClassificationController(ClassificationService classificationService, ClassificationAssembler classificationAssembler) {
        this.classificationService = classificationService;
        this.classificationAssembler = classificationAssembler;
    }

    @Override
    public ResponseEntity<Classification> creerClassification(Classification classification) {
        Classification classificationResult = classificationService.saveClassification(classification);
        return new ResponseEntity<>(classificationResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Classification> updateClassification(Long id, Classification classification) throws Exception {
        Classification classificationResult = classificationService.updateClassification(id, classification);
        return new ResponseEntity<>(classificationResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClassificationDs> findById(Long id) {
        ClassificationDs classificationResult = classificationAssembler.assembleEntityToDs(classificationService.findById(id));
        return new ResponseEntity<>(classificationResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ClassificationDs>> findAllClassifications() {
        List<ClassificationDs> classificationList = classificationAssembler.assembleEntitiesFrom(classificationService.findAllClassifications());
        return new ResponseEntity<>(classificationList, HttpStatus.OK);
    }

    @Override
    public void deleteClassification(Long id) {
        classificationService.deleteClassification(id);
    }
}
