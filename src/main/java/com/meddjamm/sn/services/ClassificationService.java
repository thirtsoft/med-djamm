package com.meddjamm.sn.services;

import com.meddjamm.sn.entity.Classification;

import java.util.List;

public interface ClassificationService {

    Classification saveClassification(Classification classification);

    Classification updateClassification(Long id, Classification classification) throws Exception;

    Classification findById(Long id);

    List<Classification> findAllClassifications();

    void deleteClassification(Long id);
}
