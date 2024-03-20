package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.assembler.MedecinAssembler;
import com.meddjamm.sn.config.assembler.UtilisateurAssembler;
import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientDs;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientListDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import com.meddjamm.sn.services.MedecinService;
import com.meddjamm.sn.utils.UtilString;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CircuitPatientAssembler {

    private final ObservationCliniqueAssembler observationCliniqueAssembler;
    private final ExamenComplementaireAssembler examenComplementaireAssembler;
    private final TraitementMedicalAssembler traitementMedicalAssembler;
    private final ConsultationAssembler consultationAssembler;
    private final OrdonnanceAssembler ordonnanceAssembler;
    private final AvisSpecialisteAssembler avisSpecialisteAssembler;
    private final SyntheseAssembler syntheseAssembler;
    private final ExamenBiologiqueAssembler examenBiologiqueAssembler;
    private final PatientAssembler patientAssembler;
    private final PatientService patientService;
    private final MedecinAssembler medecinAssembler;
    private final MedecinService medecinService;
    private final UtilisateurService utilisateurService;
    private final UtilisateurAssembler utilisateurAssembler;

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
        if (circuitPatient.getCreatedByUser() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(circuitPatient.getCreatedByUser());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            circuitPatientDs.setNomCompletMedecin(nomAgent);
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
        if (circuitPatient.getCreatedByUser() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(circuitPatient.getCreatedByUser());
            String nomMedecin = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            circuitPatientDs.setNomCompletAgent(nomMedecin);
        }
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
        circuitPatientDs.setConsultationDs(consultationAssembler.assembleEntitiesFrom(circuitPatient.getConsultations()));
        circuitPatientDs.setOrdonnanceDs(ordonnanceAssembler.assembleEntitiesFrom(circuitPatient.getOrdonnances()));
        circuitPatientDs.setAvisSpecialisteDs(avisSpecialisteAssembler.assembleEntitiesFrom(circuitPatient.getAvisSpecialistes()));
        circuitPatientDs.setExamenBiologiqueDs(examenBiologiqueAssembler.assembleEntitiesFrom(circuitPatient.getExamenBiologiques()));
        circuitPatientDs.setSyntheseDs(syntheseAssembler.assembleEntitiesFrom(circuitPatient.getSyntheseList()));
        circuitPatientDs.setNumeroCircuit(
                UtilString.createNumeroCircuitPatient(circuitPatient.getNumeroCircuit()));
        if (circuitPatient.getCode() != null) {
            Patient patient = patientService.findByCode(circuitPatient.getCode());
            PatientDetailDs patientDetailDs = patientAssembler.assemblePatientDetails(patient);
            circuitPatientDs.setPatientDetailDs(patientDetailDs);
        }
        if (circuitPatient.getCreatedByUser() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(circuitPatient.getCreatedByUser());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            circuitPatientDs.setNomCompletAgent(nomAgent);
            UtilisateurDs utilisateurDetailDs = utilisateurAssembler.assembleUtilisateurDsFromEntity(utilisateur);
            circuitPatientDs.setUtilisateurDetailDs(utilisateurDetailDs);
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
