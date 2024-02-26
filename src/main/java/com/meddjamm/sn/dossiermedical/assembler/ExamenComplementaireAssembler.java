package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.assembler.MedecinAssembler;
import com.meddjamm.sn.dossiermedical.assembler.PatientAssembler;
import com.meddjamm.sn.entity.Examen;
import com.meddjamm.sn.dossiermedical.entity.ExamenComplementaire;
import com.meddjamm.sn.dossiermedical.remote.model.ExamenComplementaireDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.ExamenComplementaireDs;
import com.meddjamm.sn.remote.model.ExamenDs;
import com.meddjamm.sn.services.MedecinService;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ExamenComplementaireAssembler {

    private final PatientService patientService;
    private final PatientAssembler patientAssembler;
    private final MedecinService medecinService;
    private final MedecinAssembler medecinAssembler;

    public ExamenComplementaireAssembler(PatientService patientService,
                                         PatientAssembler patientAssembler,
                                         MedecinService medecinService,
                                         MedecinAssembler medecinAssembler) {
        this.patientService = patientService;
        this.patientAssembler = patientAssembler;
        this.medecinService = medecinService;
        this.medecinAssembler = medecinAssembler;
    }

    public List<ExamenComplementaireDetailDs> assembleEntitiesFrom(List<ExamenComplementaire> examenComplementaires) {
        return examenComplementaires.stream().map(this::assembleEntitiesToDs).toList();
    }

    public List<ExamenComplementaire> assembleEntitiesFromDs(List<ExamenComplementaireDs> examenComplementaireDs) {
        return examenComplementaireDs.stream().map(this::assembleExamenComplementaireFromDs).toList();
    }

    public List<ExamenComplementaireDs> assembleEntitiesFromEntities(List<ExamenComplementaire> examenComplementaires) {
        return examenComplementaires.stream().map(this::assembleEntityToDs).toList();
    }

    public ExamenComplementaireDs assembleEntityToDs(ExamenComplementaire examenComplementaire) {
        ExamenComplementaireDs examenComplementaireDs = new ExamenComplementaireDs();
        if (examenComplementaire.getId() != null)
            examenComplementaireDs.setId(examenComplementaire.getId());
        examenComplementaireDs.setActif(examenComplementaire.isActif());
        examenComplementaireDs.setCreatedDate(examenComplementaire.getCreatedDate());
        examenComplementaireDs.setBiologie(examenComplementaire.getBiologie());
        examenComplementaireDs.setBiologieFileName(examenComplementaire.getBiologieFileName());
        examenComplementaireDs.setImmunologie(examenComplementaire.getImmunologie());
        examenComplementaireDs.setImmunologieFileName(examenComplementaire.getImmunologieFileName());
        examenComplementaireDs.setImagerie(examenComplementaire.getImagerie());
        examenComplementaireDs.setImagerieFileName(examenComplementaire.getImagerieFileName());
        examenComplementaireDs.setAnatomopathologie(examenComplementaire.getAnatomopathologie());
        examenComplementaireDs.setAnatomopathologieFileName(examenComplementaire.getAnatomopathologieFileName());
        examenComplementaireDs.setCircuitPatientId(examenComplementaire.getCircuitPatientId());
        examenComplementaireDs.setCreatedBy(examenComplementaire.getCreatedBy());
        return examenComplementaireDs;
    }

    public ExamenComplementaire assembleExamenComplementaireFromDs(ExamenComplementaireDs examenComplementaireDs) {
        ExamenComplementaire examenComplementaire = new ExamenComplementaire();
        if (examenComplementaireDs.getId() != null)
            examenComplementaire.setId(examenComplementaireDs.getId());
        examenComplementaire.setActif(examenComplementaireDs.isActif());
        examenComplementaire.setCreatedDate(examenComplementaireDs.getCreatedDate());
        examenComplementaire.setBiologie(examenComplementaireDs.getBiologie());
        examenComplementaire.setBiologieFileName(examenComplementaireDs.getBiologieFileName());
        examenComplementaire.setImmunologie(examenComplementaireDs.getImmunologie());
        examenComplementaire.setImmunologieFileName(examenComplementaireDs.getImmunologieFileName());
        examenComplementaire.setImagerie(examenComplementaireDs.getImagerie());
        examenComplementaire.setImagerieFileName(examenComplementaireDs.getImagerieFileName());
        examenComplementaire.setAnatomopathologie(examenComplementaireDs.getAnatomopathologie());
        examenComplementaire.setAnatomopathologieFileName(examenComplementaireDs.getAnatomopathologieFileName());
        examenComplementaire.setCircuitPatientId(examenComplementaireDs.getCircuitPatientId());
        examenComplementaire.setCreatedBy(examenComplementaireDs.getCreatedBy());
        return examenComplementaire;
    }

    public ExamenComplementaireDetailDs assembleEntitiesToDs(ExamenComplementaire examenComplementaire) {
        ExamenComplementaireDetailDs examenComplementaireDs = new ExamenComplementaireDetailDs();
        if (examenComplementaire.getId() != null)
            examenComplementaireDs.setId(examenComplementaire.getId());
        examenComplementaireDs.setActif(examenComplementaire.isActif());
        examenComplementaireDs.setCreatedDate(examenComplementaire.getCreatedDate());
        examenComplementaireDs.setBiologie(examenComplementaire.getBiologie());
        examenComplementaireDs.setBiologieFileName(examenComplementaire.getBiologieFileName());
        examenComplementaireDs.setImmunologie(examenComplementaire.getImmunologie());
        examenComplementaireDs.setImmunologieFileName(examenComplementaire.getImmunologieFileName());
        examenComplementaireDs.setImagerie(examenComplementaire.getImagerie());
        examenComplementaireDs.setImagerieFileName(examenComplementaire.getImagerieFileName());
        examenComplementaireDs.setAnatomopathologie(examenComplementaire.getAnatomopathologie());
        examenComplementaireDs.setAnatomopathologieFileName(examenComplementaire.getAnatomopathologieFileName());
        examenComplementaireDs.setCircuitPatientId(examenComplementaire.getCircuitPatientId());
        examenComplementaireDs.setCreatedBy(examenComplementaire.getCreatedBy());
        return examenComplementaireDs;
    }

    public List<ExamenDs> createListExamenDs(Set<Examen> examenSet) {
        if (examenSet == null)
            return Collections.emptyList();
        List<ExamenDs> dtos = new ArrayList<>();
        for (Examen examen : examenSet) {
            dtos.add(assembleEntityToDs(examen));
        }
        return dtos;
    }

    public Set<Examen> createSetExamens(List<ExamenDs> examenDs) {
        if (examenDs == null)
            return null;
        Set<Examen> actions = new HashSet<>();
        for (ExamenDs dto : examenDs)
            if (dto != null)
                actions.add(assembleExamenFromDs(dto));
        return actions;
    }

    public ExamenDs assembleEntityToDs(Examen examen) {
        ExamenDs examenDs = new ExamenDs();
        examenDs.setId(examen.getId());
        examenDs.setLibelle(examen.getLibelle());
        return examenDs;
    }

    public Examen assembleExamenFromDs(ExamenDs examenDs) {
        Examen examen = new Examen();
        examen.setId(examenDs.getId());
        examen.setLibelle(examenDs.getLibelle());
        return examen;
    }

}