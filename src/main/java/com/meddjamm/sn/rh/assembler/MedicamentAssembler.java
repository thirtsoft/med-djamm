package com.meddjamm.sn.rh.assembler;

import com.meddjamm.sn.rh.entity.Medicament;
import com.meddjamm.sn.rh.remote.model.MedicamentDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicamentAssembler {

    public List<MedicamentDs> assembleEntitiesFrom(List<Medicament> medicaments) {
        return medicaments.stream().map(this::assembleEntityToDs).toList();
    }

    public MedicamentDs assembleEntityToDs(Medicament medicament) {
        MedicamentDs medicamentDs = new MedicamentDs();
        if (medicament.getId() != null)
            medicamentDs.setId(medicament.getId());
        medicamentDs.setCode(medicament.getCode());
        medicamentDs.setLibelle(medicament.getLibelle());
        medicamentDs.setCreateDate(medicament.getCreateDate());
        medicamentDs.setActif(medicament.isActif());
        return medicamentDs;
    }

    public Medicament assembleMedicamentFromDs(MedicamentDs medicamentDs) {
        Medicament medicament = new Medicament();
        if (medicamentDs.getId() != null)
            medicament.setId(medicamentDs.getId());
        medicament.setCode(medicamentDs.getCode());
        medicament.setLibelle(medicamentDs.getLibelle());
        medicament.setCreateDate(medicamentDs.getCreateDate());
        medicament.setActif(medicamentDs.isActif());
        return medicament;
    }
}
