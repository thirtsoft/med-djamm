package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.assembler.MedecinAssembler;
import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientDs;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientListDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.entity.Medecin;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.remote.model.*;
import com.meddjamm.sn.services.MedecinService;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import com.meddjamm.sn.utils.UtilString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CircuitPatientAssembler {

    private final ObservationCliniqueAssembler observationCliniqueAssembler;
    private final ExamenComplementaireAssembler examenComplementaireAssembler;
    private final TraitementMedicalAssembler traitementMedicalAssembler;
    private final PatientAssembler patientAssembler;
    private final PatientService patientService;
    private final MedecinAssembler medecinAssembler;
    private final MedecinService medecinService;

    public CircuitPatientAssembler(ObservationCliniqueAssembler observationCliniqueAssembler,
                                   ExamenComplementaireAssembler examenComplementaireAssembler,
                                   TraitementMedicalAssembler traitementMedicalAssembler,
                                   PatientAssembler patientAssembler,
                                   PatientService patientService,
                                   MedecinAssembler medecinAssembler,
                                   MedecinService medecinService) {
        this.observationCliniqueAssembler = observationCliniqueAssembler;
        this.examenComplementaireAssembler = examenComplementaireAssembler;
        this.traitementMedicalAssembler = traitementMedicalAssembler;
        this.patientAssembler = patientAssembler;
        this.patientService = patientService;
        this.medecinAssembler = medecinAssembler;
        this.medecinService = medecinService;
    }

    public List<CircuitPatientListDs> assembleEntitiesFrom(List<CircuitPatient> circuitPatients) {
        return circuitPatients.stream().map(this::assembleEntityToListDs).toList();
    }

    public CircuitPatientListDs assembleEntityToListDs(CircuitPatient circuitPatient) {
        CircuitPatientListDs circuitPatientDs = new CircuitPatientListDs();
        if (circuitPatient.getId() != null)
            circuitPatientDs.setId(circuitPatient.getId());
        circuitPatientDs.setEtat(circuitPatient.getEtat());
        circuitPatientDs.setActif(circuitPatient.isActif());
        circuitPatientDs.setType(circuitPatient.getType());
        circuitPatientDs.setCreateDate(circuitPatient.getCreateDate());
        circuitPatientDs.setCreatedBy(circuitPatient.getCreatedBy());
        circuitPatientDs.setNumeroCircuit(
                UtilString.createNumeroCircuitPatient(circuitPatient.getNumeroCircuit()));
        if (circuitPatient.getCode() != null) {
            Patient patient = patientService.findByCode(circuitPatient.getCode());
            String nomPatient = patient.getPrenom() + ' ' + patient.getNom();
            circuitPatientDs.setNomCompletPatient(nomPatient);
        }
        if (circuitPatient.getMatricule() != null) {
            Medecin medecin = medecinService.findByMatricule(circuitPatient.getMatricule());
            String nomMedecin = medecin.getPrenom() + ' ' + medecin.getNom();
            circuitPatientDs.setNomCompletMedecin(nomMedecin);
        }
        return circuitPatientDs;
    }


    public CircuitPatientDs assembleEntityToDs(CircuitPatient circuitPatient) {
        CircuitPatientDs circuitPatientDs = new CircuitPatientDs();
        if (circuitPatient.getId() != null)
            circuitPatientDs.setId(circuitPatient.getId());
        circuitPatientDs.setCode(circuitPatient.getCode());
        circuitPatientDs.setMatricule(circuitPatient.getMatricule());
        circuitPatientDs.setEtat(circuitPatient.getEtat());
        circuitPatientDs.setActif(circuitPatient.isActif());
        circuitPatientDs.setType(circuitPatient.getType());
        circuitPatientDs.setCreateDate(circuitPatient.getCreateDate());
        circuitPatientDs.setCreatedBy(circuitPatient.getCreatedBy());
        circuitPatientDs.setNumeroCircuit(
                UtilString.createNumeroCircuitPatient(circuitPatient.getNumeroCircuit()));
        return circuitPatientDs;
    }

    public CircuitPatient assembleCircuitPatientFromDs(CircuitPatientDs circuitPatientDs) {
        CircuitPatient circuitPatient = new CircuitPatient();
        if (circuitPatientDs.getId() != null)
            circuitPatient.setId(circuitPatientDs.getId());
        circuitPatient.setCode(circuitPatientDs.getCode());
        circuitPatient.setMatricule(circuitPatientDs.getMatricule());
        circuitPatient.setEtat(circuitPatientDs.getEtat());
        circuitPatient.setActif(circuitPatientDs.isActif());
        circuitPatient.setType(circuitPatientDs.getType());
        circuitPatient.setCreateDate(circuitPatientDs.getCreateDate());
        circuitPatient.setCreatedBy(circuitPatientDs.getCreatedBy());
       // circuitPatient.setObservationCliniqueList(observationCliniqueAssembler.assembleEntitiesFromDs(circuitPatientDs.getO))
      //  circuitPatient.setExamenComplementaires(examenComplementaireAssembler.assembleEntitiesFromDs(circuitPatientDs.getExamenComplementaireDs()));
      //  circuitPatient.setTraitementMedicals(traitementMedicalAssembler.assembleEntitiesFromDs(circuitPatientDs.getTraitementMedicalDs()));
        return circuitPatient;
    }

    public CircuitPatientDetailDs assembleEntityToDetailDs(CircuitPatient circuitPatient) {
        CircuitPatientDetailDs circuitPatientDs = new CircuitPatientDetailDs();
        circuitPatientDs.setId(circuitPatient.getId());
        circuitPatientDs.setCode(circuitPatient.getCode());
        circuitPatientDs.setMatricule(circuitPatient.getMatricule());
        circuitPatientDs.setEtat(circuitPatient.getEtat());
        circuitPatientDs.setActif(circuitPatient.isActif());
        circuitPatientDs.setType(circuitPatient.getType());
        circuitPatientDs.setCreateDate(circuitPatient.getCreateDate());
        circuitPatientDs.setCreatedBy(circuitPatient.getCreatedBy());
        circuitPatientDs.setObservationCliniqueDs(observationCliniqueAssembler.assembleEntitiesFrom(circuitPatient.getObservationCliniqueList()));
        circuitPatientDs.setExamenComplementaireDs(examenComplementaireAssembler.assembleEntitiesFromEntities(circuitPatient.getExamenComplementaires()));
        circuitPatientDs.setTraitementMedicalDs(traitementMedicalAssembler.assembleEntitiesFrom(circuitPatient.getTraitementMedicals()));
        circuitPatientDs.setNumeroCircuit(
                UtilString.createNumeroCircuitPatient(circuitPatient.getNumeroCircuit()));
        if (circuitPatient.getCode() != null) {
            Patient patient = patientService.findByCode(circuitPatient.getCode());
            PatientDetailDs patientDetailDs = patientAssembler.assemblePatientDetails(patient);
            circuitPatientDs.setPatientDetailDs(patientDetailDs);
        }
        if (circuitPatient.getMatricule() != null) {
            Medecin medecin = medecinService.findByMatricule(circuitPatient.getMatricule());
            MedecinDetailDs medecinDetailDs = medecinAssembler.assembleEntitiesToDs(medecin);
            circuitPatientDs.setMedecinDetailDs(medecinDetailDs);
        }
        return circuitPatientDs;
    }

    /*
    public CircuitPatient assembleCircuitPatientFromDetailDs(CircuitPatientDetailDs circuitPatientDs) {
        CircuitPatient circuitPatient = new CircuitPatient();
        circuitPatient.setId(circuitPatientDs.getId());
        circuitPatient.setCode(circuitPatientDs.getCode());
        circuitPatient.setMatricule(circuitPatientDs.getMatricule());
        circuitPatient.setEtat(circuitPatientDs.getEtat());
        circuitPatient.setActif(circuitPatientDs.isActif());
        circuitPatient.setType(circuitPatientDs.getType());
        circuitPatient.setCreateDate(circuitPatientDs.getCreateDate());
        circuitPatient.setCreatedBy(circuitPatientDs.getCreatedBy());
        circuitPatient.setObservationCliniqueList(observationCliniqueAssembler.assembleEntitiesFromDs(circuitPatientDs.getObservationCliniqueDs()));
        circuitPatient.setExamenComplementaires(examenComplementaireAssembler.assembleEntitiesFromDs(circuitPatientDs.getExamenComplementaireDs()));
        circuitPatient.setTraitementMedicals(traitementMedicalAssembler.assembleEntitiesFromDs(circuitPatientDs.getTraitementMedicalDs()));
        return circuitPatient;
    }*/
}
