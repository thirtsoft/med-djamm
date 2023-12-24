package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.Classification;
import com.meddjamm.sn.remote.model.ClassificationDs;
import org.springframework.stereotype.Component;

@Component
public class ClassificationAssembler {

    public ClassificationDs assembleEntityToDs(Classification classification) {
        return new ClassificationDs(classification.getLibelle());
    }
}