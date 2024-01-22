package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.Atteinte;
import com.meddjamm.sn.remote.model.AtteinteDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtteinteAssembler {

    public List<AtteinteDs> assembleEntitiesFrom(List<Atteinte> atteintes) {
        return atteintes.stream().map(this::assembleEntityToDs).toList();
    }

    public AtteinteDs assembleEntityToDs(Atteinte atteinte) {
        AtteinteDs atteinteDs = new AtteinteDs();
        atteinteDs.setId(atteinte.getId());
        atteinteDs.setLibelle(atteinte.getLibelle());
        atteinteDs.setCreateDate(atteinte.getCreateDate());
        atteinteDs.setActif(atteinte.isActif());
        return atteinteDs;
    }

    public Atteinte assembleAtteinteFromDs(AtteinteDs atteinteDs) {
        Atteinte atteinte = new Atteinte();
        atteinte.setId(atteinteDs.getId());
        atteinte.setLibelle(atteinteDs.getLibelle());
        atteinte.setCreateDate(atteinteDs.getCreateDate());
        atteinte.setActif(atteinteDs.isActif());
        return atteinte;
    }
}
