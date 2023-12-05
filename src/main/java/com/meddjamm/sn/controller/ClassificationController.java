package com.meddjamm.sn.controller;

import com.meddjamm.sn.controller.api.ClassificationApi;
import com.meddjamm.sn.model.Classification;
import com.meddjamm.sn.services.ClassificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ClassificationController implements ClassificationApi {

    private final ClassificationService classificationService;

    public ClassificationController(ClassificationService classificationService) {
        this.classificationService = classificationService;
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
    public ResponseEntity<Classification> findById(Long id) {
        Classification classificationResult = classificationService.findById(id);
        return new ResponseEntity<>(classificationResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Classification>> findAllClassifications() {
        List<Classification> classificationList = classificationService.findAllClassifications();
        return new ResponseEntity<>(classificationList, HttpStatus.OK);
    }

    @Override
    public void deleteClassification(Long id) {
        classificationService.deleteClassification(id);
    }
}
