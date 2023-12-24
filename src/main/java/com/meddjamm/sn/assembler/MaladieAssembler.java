package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.Maladie;
import com.meddjamm.sn.remote.model.MaladieDs;
import org.springframework.stereotype.Component;

@Component
public class MaladieAssembler {

    public MaladieDs assembleEntityToDs(Maladie maladie) {
        return new MaladieDs(maladie.getLibelle());
    }
}