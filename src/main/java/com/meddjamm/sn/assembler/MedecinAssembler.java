package com.meddjamm.sn.assembler;

import com.meddjamm.sn.entity.Medecin;
import com.meddjamm.sn.rh.assembler.SpecialiteAssembler;
import com.meddjamm.sn.rh.entity.Specialite;
import com.meddjamm.sn.remote.model.MedecinDetailDs;
import com.meddjamm.sn.remote.model.MedecinDs;
import com.meddjamm.sn.rh.services.SpecialiteService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedecinAssembler {

    private final SpecialiteAssembler specialiteAssembler;

    private final SpecialiteService specialiteService;

    public MedecinAssembler(SpecialiteAssembler specialiteAssembler,
                            SpecialiteService specialiteService) {
        this.specialiteAssembler = specialiteAssembler;
        this.specialiteService = specialiteService;
    }

    public List<MedecinDetailDs> assembleEntitiesFrom(List<Medecin> medecins) {
        return medecins.stream().map(this::assembleEntitiesToDs).toList();
    }

    public MedecinDs assembleEntityToDs(Medecin medecin) {
        MedecinDs medecinDs = new MedecinDs();
        medecinDs.setId(medecin.getId());
        medecinDs.setMatricule(medecin.getMatricule());
        medecinDs.setPrenom(medecin.getPrenom());
        medecinDs.setNom(medecin.getNom());
        medecinDs.setSexe(medecin.getSexe());
        medecinDs.setCivilite(medecin.getCivilite());
        medecinDs.setTelephone(medecin.getTelephone());
        medecinDs.setEmail(medecin.getEmail());
        medecinDs.setSpeciality(medecin.getSpeciality());
        medecinDs.setDateRecrutement(medecin.getDateRecrutement());
        medecinDs.setActif(medecin.isActif());
        return medecinDs;
    }

    public Medecin assembleMedecinFromDs(MedecinDs medecinDs) {
        Medecin medecin = new Medecin();
        medecin.setId(medecinDs.getId());
        medecin.setMatricule(medecinDs.getMatricule());
        medecin.setPrenom(medecinDs.getPrenom());
        medecin.setNom(medecinDs.getNom());
        medecin.setSexe(medecinDs.getSexe());
        medecin.setCivilite(medecinDs.getCivilite());
        medecin.setTelephone(medecinDs.getTelephone());
        medecin.setEmail(medecinDs.getEmail());
        medecin.setSpeciality(medecinDs.getSpeciality());
        medecin.setDateRecrutement(medecinDs.getDateRecrutement());
        medecin.setActif(medecinDs.isActif());
        return medecin;
    }

    public MedecinDetailDs assembleEntitiesToDs(Medecin medecin) {
        MedecinDetailDs medecinDs = new MedecinDetailDs();
        medecinDs.setId(medecin.getId());
        medecinDs.setMatricule(medecin.getMatricule());
        medecinDs.setPrenom(medecin.getPrenom());
        medecinDs.setNom(medecin.getNom());
        medecinDs.setSexe(medecin.getSexe());
        medecinDs.setCivilite(medecin.getCivilite());
        medecinDs.setTelephone(medecin.getTelephone());
        medecinDs.setEmail(medecin.getEmail());
        medecinDs.setActif(medecin.isActif());
        if (medecin.getSpeciality() != null) {
            Specialite specialite = specialiteService.findById(medecin.getSpeciality());
            medecinDs.setSpecialiteDs(specialiteAssembler.assembleSpecialiteFrom(specialite));
        }
        medecinDs.setDateRecrutement(medecin.getDateRecrutement());
        return medecinDs;
    }
}
