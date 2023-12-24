package com.meddjamm.sn.services.Impl;

import com.meddjamm.sn.entity.Classification;
import com.meddjamm.sn.repository.ClassificationRepository;
import com.meddjamm.sn.services.ClassificationService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClassificationServiceImpl implements ClassificationService {

    private final ClassificationRepository classificationRepository;

    public ClassificationServiceImpl(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public Classification saveClassification(Classification classification) {
        classification.setActif(true);
        classification.setCreateDate(new Date());
        return classificationRepository.save(classification);
    }

    @Override
    public Classification updateClassification(Long id, Classification classification) throws Exception {
        if (!classificationRepository.existsById(id)) {
            throw new Exception("This Classification is not found");
        }
        Classification classificationResult = classificationRepository.findClassificationById(id);
        if (classificationResult == null) {
            throw new Exception("This Classification is not found");
        }
        classificationResult.setLibelle(classification.getLibelle());
        return classificationRepository.save(classificationResult);
    }

    @Override
    public Classification findById(Long id) {
        return classificationRepository.findClassificationById(id);
    }

    @Override
    public List<Classification> findAllClassifications() {
        return classificationRepository.findAll();
    }

    @Override
    public void deleteClassification(Long id) {
        Classification classification = classificationRepository.findClassificationById(id);
        classification.setActif(false);
        classificationRepository.save(classification);
    }
}
