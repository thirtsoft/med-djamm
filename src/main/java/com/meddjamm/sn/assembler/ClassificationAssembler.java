package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.Classification;
import com.meddjamm.sn.remote.model.ClassificationDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassificationAssembler {

    public List<ClassificationDs> assembleEntitiesFrom(List<Classification> classifications) {
        return classifications.stream().map(this::assembleEntityToDs).toList();
    }

    public ClassificationDs assembleEntityToDs(Classification classification) {
        ClassificationDs classificationDs = new ClassificationDs();
        classificationDs.setId(classification.getId());
        classificationDs.setLibelle(classification.getLibelle());
        classificationDs.setCreateDate(classification.getCreateDate());
        classificationDs.setActif(classification.isActif());
        return classificationDs;
    }

    public Classification assembleClassificationFromDs(ClassificationDs classificationDs) {
        Classification classification = new Classification();
        classification.setId(classificationDs.getId());
        classification.setLibelle(classificationDs.getLibelle());
        classification.setCreateDate(classificationDs.getCreateDate());
        classification.setActif(classificationDs.isActif());
        return classification;
    }
}