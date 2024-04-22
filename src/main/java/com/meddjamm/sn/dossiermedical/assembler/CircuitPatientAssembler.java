package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.assembler.UtilisateurAssembler;
import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.AvisSpecialiste;
import com.meddjamm.sn.dossiermedical.entity.CircuitPatient;
import com.meddjamm.sn.dossiermedical.entity.ConsultationMedical;
import com.meddjamm.sn.dossiermedical.entity.Ordonnance;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.remote.model.AllCircuitPatientDs;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientDs;
import com.meddjamm.sn.dossiermedical.remote.model.CircuitPatientListDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.dossiermedical.services.PatientService;
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
    private final UtilisateurService utilisateurService;
    private final UtilisateurAssembler utilisateurAssembler;
    private final DiscussionAssembler discussionAssembler;
    private final ConsultationMedicalAssembler consultationMedicalAssembler;

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
        circuitPatient.setActif(circuitPatientDs.isActif());
        return circuitPatient;
    }

    public CircuitPatientDetailDs assembleEntityToDetailDs(CircuitPatient circuitPatient) {
        CircuitPatientDetailDs circuitPatientDs = new CircuitPatientDetailDs();
        if (circuitPatient.getId() != null)
            circuitPatientDs.setId(circuitPatient.getId());
        circuitPatientDs.setCode(circuitPatient.getCode());
        circuitPatientDs.setMatricule(circuitPatient.getMatricule());
        circuitPatientDs.setEtat(circuitPatient.getEtat());
        circuitPatientDs.setActif(circuitPatient.isActif());
        circuitPatientDs.setType(circuitPatient.getType());
        circuitPatientDs.setCreateDate(circuitPatient.getCreateDate());
        circuitPatientDs.setCreatedBy(circuitPatient.getCreatedBy());
//        circuitPatientDs.setObservationCliniqueDs(observationCliniqueAssembler.assembleEntitiesFrom(circuitPatient.getObservationCliniqueList()));
//        circuitPatientDs.setExamenComplementaireDs(examenComplementaireAssembler.assembleEntitiesFromEntities(circuitPatient.getExamenComplementaires()));
//        circuitPatientDs.setTraitementMedicalDs(traitementMedicalAssembler.assembleEntitiesFrom(circuitPatient.getTraitementMedicals()));
        //    circuitPatientDs.setConsultationDs(consultationAssembler.assembleEntitiesFrom(circuitPatient.getConsultations()));
        circuitPatientDs.setConsultationMedicalDs(consultationMedicalAssembler.assembleEntitiesFrom(circuitPatient.getConsultationMedicals()));
        circuitPatientDs.setOrdonnanceDs(ordonnanceAssembler.assembleEntitiesFrom(circuitPatient.getOrdonnances()));
        circuitPatientDs.setAvisSpecialisteDs(avisSpecialisteAssembler.assembleEntitiesFrom(circuitPatient.getAvisSpecialistes()));
        //   circuitPatientDs.setExamenBiologiqueDs(examenBiologiqueAssembler.assembleEntitiesFrom(circuitPatient.getExamenBiologiques()));
//        circuitPatientDs.setSyntheseDs(syntheseAssembler.assembleEntitiesFrom(circuitPatient.getSyntheseList()));
//        circuitPatientDs.setDiscussionDs(discussionAssembler.assembleEntitiesFrom(circuitPatient.getDiscussions()));
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

    public List<AllCircuitPatientDs> assembleAllCircuitsDsFromAllCircuits(List<CircuitPatient> circuitPatients) {
        return circuitPatients.stream().map(this::createAllCircuitDs).toList();
    }

    public AllCircuitPatientDs createAllCircuitDs(CircuitPatient circuitPatient) {
        if (circuitPatient == null)
            return null;
        AllCircuitPatientDs circuitPatientDs = new AllCircuitPatientDs();
        circuitPatientDs.setId(circuitPatient.getId());
        circuitPatientDs.setCode(circuitPatient.getCode());
        circuitPatientDs.setEtat(circuitPatient.getEtat());
        circuitPatientDs.setActif(circuitPatient.isActif());
        circuitPatientDs.setCreateDate(circuitPatient.getCreateDate());
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
            circuitPatientDs.setNomCompletAgent(nomAgent);
        }
        if (!circuitPatient.getConsultationMedicals().isEmpty()) {
            for (ConsultationMedical consultation : circuitPatient.getConsultationMedicals()) {
                circuitPatientDs.setType("Consultation");
            }
        }
        if (!circuitPatient.getOrdonnances().isEmpty()) {
            for (Ordonnance ordonnance : circuitPatient.getOrdonnances()) {
                circuitPatientDs.setType("Ordonnance");
            }
        }
        if (!circuitPatient.getAvisSpecialistes().isEmpty()) {
            for (AvisSpecialiste avisSpecialiste : circuitPatient.getAvisSpecialistes()) {
                circuitPatientDs.setType("Avis spécialiste");
            }
        }
        return circuitPatientDs;
    }
}
