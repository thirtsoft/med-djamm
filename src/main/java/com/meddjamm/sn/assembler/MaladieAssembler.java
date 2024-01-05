package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.Maladie;
import com.meddjamm.sn.remote.model.MaladieDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MaladieAssembler {

    public List<MaladieDs> assembleEntitiesFrom(List<Maladie> maladies) {
        return maladies.stream().map(this::assembleEntityToDs).toList();
    }

    public MaladieDs assembleEntityToDs(Maladie maladie) {
        MaladieDs maladieDs = new MaladieDs();
        maladieDs.setId(maladie.getId());
        maladieDs.setLibelle(maladie.getLibelle());
        maladieDs.setCreateDate(maladie.getCreateDate());
        maladieDs.setActif(maladie.isActif());
        return maladieDs;
    }

    public Maladie assembleMaladieFromDs(MaladieDs maladieDs) {
        Maladie maladie = new Maladie();
        maladie.setId(maladieDs.getId());
        maladie.setLibelle(maladieDs.getLibelle());
        maladie.setCreateDate(maladieDs.getCreateDate());
        maladie.setActif(maladieDs.isActif());
        return maladie;
    }
}