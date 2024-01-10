package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.ModeVie;
import com.meddjamm.sn.remote.model.ModeVieDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModeVieAssembler {

    public List<ModeVieDs> assembleEntitiesFrom(List<ModeVie> modeVies) {
        return modeVies.stream().map(this::assembleEntityToDs).toList();
    }

    public ModeVieDs assembleEntityToDs(ModeVie modeVie) {
        ModeVieDs modeVieDs = new ModeVieDs();
        modeVieDs.setId(modeVie.getId());
        modeVieDs.setLibelle(modeVie.getLibelle());
        modeVieDs.setIndexPatient(modeVie.getIndexPatient());
        modeVieDs.setCreatedDate(modeVie.getCreatedDate());
        modeVieDs.setActif(modeVie.isActif());
        return modeVieDs;
    }

    public ModeVie assembleModeVieFromDs(ModeVieDs modeVieDs) {
        ModeVie modeVie = new ModeVie();
        modeVie.setId(modeVieDs.getId());
        modeVie.setLibelle(modeVieDs.getLibelle());
        modeVie.setIndexPatient(modeVieDs.getIndexPatient());
        modeVie.setCreatedDate(modeVieDs.getCreatedDate());
        modeVie.setActif(modeVieDs.isActif());
        return modeVie;
    }
}
