package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.assembler.UtilisateurAssembler;
import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.Hospitalisation;
import com.meddjamm.sn.dossiermedical.entity.Patient;
import com.meddjamm.sn.dossiermedical.remote.model.HospitalisationDetailDs;
import com.meddjamm.sn.dossiermedical.remote.model.HospitalisationDs;
import com.meddjamm.sn.dossiermedical.remote.model.HospitalisationListDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.dossiermedical.services.PatientService;
import com.meddjamm.sn.utils.UtilString;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HospitalisationAssembler {

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

    public List<HospitalisationListDs> assembleEntitiesFrom(List<Hospitalisation> hospitalisationList) {
        return hospitalisationList.stream().map(this::assembleEntityToListDs).toList();
    }

    public List<HospitalisationDetailDs> assembleHospitalisationsDetailsFromEntity(List<Hospitalisation> hospitalisationList) {
        return hospitalisationList.stream().map(this::assembleHospitalisationDetailDsFromHospitalisation).toList();
    }

    public HospitalisationListDs assembleEntityToListDs(Hospitalisation hospitalisation) {
        HospitalisationListDs hospitalisationListDs = new HospitalisationListDs();
        if (hospitalisation.getId() != null)
            hospitalisationListDs.setId(hospitalisation.getId());
        hospitalisationListDs.setActif(hospitalisation.isActif());
        hospitalisationListDs.setResume(hospitalisation.getResume());
        hospitalisationListDs.setCreateDate(hospitalisation.getCreationDate());
        hospitalisationListDs.setCreatedByUser(hospitalisation.getCreatedByUser());
        hospitalisationListDs.setEst_Transfer(hospitalisation.getEst_Transfer());
        hospitalisationListDs.setNumeroHospitalisation(
                UtilString.createNumeroHospitalisation(hospitalisation.getNumeroHospitalisation())
        );
        if (hospitalisation.getCode() != null) {
            Patient patient = patientService.findByCode(hospitalisation.getCode());
            String nomPatient = patient.getPrenom() + ' ' + patient.getNom();
            hospitalisationListDs.setNomCompletPatient(nomPatient);
        }
        if (hospitalisation.getCreatedByUser() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(hospitalisation.getCreatedByUser());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            hospitalisationListDs.setNomCompletMedecin(nomAgent);
        }
        return hospitalisationListDs;
    }

    public HospitalisationDs assembleEntityToDs(Hospitalisation hospitalisation) {
        HospitalisationDs hospitalisationDs = new HospitalisationDs();
        if (hospitalisation.getId() != null)
            hospitalisationDs.setId(hospitalisation.getId());
        hospitalisationDs.setCode(hospitalisation.getCode());
        hospitalisationDs.setMatricule(hospitalisation.getMatricule());
        hospitalisationDs.setResume(hospitalisation.getResume());
        hospitalisationDs.setActif(hospitalisation.isActif());
        hospitalisationDs.setEst_Transfer(hospitalisation.getEst_Transfer());
        hospitalisationDs.setNumeroHospitalisation(
                UtilString.createNumeroHospitalisation(hospitalisation.getNumeroHospitalisation()));
        hospitalisationDs.setObservationCliniqueDs(observationCliniqueAssembler.assembleEntityToDs(
                hospitalisation.getObservationClinique()
        ));
        //    hospitalisationDs.setObservationCliniqueDsList(observationCliniqueAssembler.assembleEntitiesFrom(hospitalisation.getObservationCliniqueList()));
        hospitalisationDs.setExamenComplementaireDsList(examenComplementaireAssembler.assembleEntitiesFromEntities(hospitalisation.getExamenComplementaires()));
        hospitalisationDs.setTraitementMedicalDsList(traitementMedicalAssembler.assembleEntitiesFrom(hospitalisation.getTraitementMedicals()));
        hospitalisationDs.setSyntheseDsList(syntheseAssembler.assembleEntitiesFrom(hospitalisation.getSyntheseList()));
        hospitalisationDs.setDiscussionDsList(discussionAssembler.assembleEntitiesFrom(hospitalisation.getDiscussions()));
        return hospitalisationDs;
    }

    public Hospitalisation assembleDsToEntity(HospitalisationDs hospitalisationDs) {
        Hospitalisation hospitalisation = new Hospitalisation();
        if (hospitalisationDs.getId() != null)
            hospitalisation.setId(hospitalisationDs.getId());
        hospitalisation.setCode(hospitalisationDs.getCode());
        hospitalisation.setMatricule(hospitalisationDs.getMatricule());
        hospitalisation.setResume(hospitalisationDs.getResume());
        hospitalisation.setActif(hospitalisationDs.isActif());
        hospitalisation.setEst_Transfer(hospitalisationDs.getEst_Transfer());
        /*
        hospitalisation.setNumeroHospitalisation(
                UtilString.formatNumeroHospitalisation(hospitalisationDs.getNumeroHospitalisation()));*/
        hospitalisation.setObservationClinique(observationCliniqueAssembler.assembleObservationCliniqueFromDs(
                hospitalisationDs.getObservationCliniqueDs()
        ));
        //    hospitalisation.setObservationCliniqueList(observationCliniqueAssembler.assembleEntitiesFromDs(hospitalisationDs.getObservationCliniqueDsList()));
        hospitalisation.setExamenComplementaires(examenComplementaireAssembler.assembleEntitiesFromDs(hospitalisationDs.getExamenComplementaireDsList()));
        hospitalisation.setTraitementMedicals(traitementMedicalAssembler.assembleEntitiesFromDs(hospitalisationDs.getTraitementMedicalDsList()));
        hospitalisation.setSyntheseList(syntheseAssembler.assembleEntitiesFromDs(hospitalisationDs.getSyntheseDsList()));
        hospitalisation.setDiscussions(discussionAssembler.assembleEntitiesFromDs(hospitalisationDs.getDiscussionDsList()));
        return hospitalisation;
    }

    public HospitalisationDetailDs assembleHospitalisationDetailDsFromHospitalisation(Hospitalisation hospitalisation) {
        if (hospitalisation == null)
            return null;
        HospitalisationDetailDs hospitalisationDetailDs = new HospitalisationDetailDs();
        if (hospitalisation.getId() != null)
            hospitalisationDetailDs.setId(hospitalisation.getId());
        hospitalisationDetailDs.setResume(hospitalisation.getResume());
        hospitalisationDetailDs.setEst_Transfer(hospitalisation.getEst_Transfer());
        hospitalisationDetailDs.setActif(hospitalisation.isActif());
        hospitalisationDetailDs.setCreateDate(hospitalisation.getCreationDate());
        hospitalisationDetailDs.setObservationCliniqueDs(observationCliniqueAssembler.assembleEntityToDs(hospitalisation.getObservationClinique()));
        //    hospitalisationDetailDs.setObservationCliniqueDsList(observationCliniqueAssembler.assembleEntitiesFrom(hospitalisation.getObservationCliniqueList()));
        hospitalisationDetailDs.setExamenComplementaireDsList(examenComplementaireAssembler.assembleEntitiesFromEntities(hospitalisation.getExamenComplementaires()));
        hospitalisationDetailDs.setTraitementMedicalDsList(traitementMedicalAssembler.assembleEntitiesFrom(hospitalisation.getTraitementMedicals()));
        hospitalisationDetailDs.setSyntheseDsList(syntheseAssembler.assembleEntitiesFrom(hospitalisation.getSyntheseList()));
        hospitalisationDetailDs.setDiscussionDsList(discussionAssembler.assembleEntitiesFrom(hospitalisation.getDiscussions()));
        hospitalisationDetailDs.setNumeroHospitalisation(
                UtilString.createNumeroHospitalisation(hospitalisation.getNumeroHospitalisation())
        );
        if (hospitalisation.getCode() != null) {
            Patient patient = patientService.findByCode(hospitalisation.getCode());
            PatientDetailDs patientDetailDs = patientAssembler.assemblePatientDetails(patient);
            hospitalisationDetailDs.setPatientDetailDs(patientDetailDs);
        }
        if (hospitalisation.getCreatedByUser() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(hospitalisation.getCreatedByUser());
            UtilisateurDs utilisateurDs = utilisateurAssembler.assembleUtilisateurDsFromEntity(utilisateur);
            hospitalisationDetailDs.setUtilisateurDs(utilisateurDs);
            hospitalisationDetailDs.setNomCompletMedecin(utilisateur.getPrenom() + ' ' + utilisateur.getNom());
        }
        return hospitalisationDetailDs;
    }


}
