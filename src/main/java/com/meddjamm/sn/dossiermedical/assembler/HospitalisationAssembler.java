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
import com.meddjamm.sn.dossiermedical.repository.HospitalisationRepository;
import com.meddjamm.sn.dossiermedical.services.HospitalisationService;
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
    ;

    private final HospitalisationRepository hospitalisationRepository;

    private final HospitalisationService hospitalisationService;

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
        hospitalisationListDs.setCreateDate(hospitalisation.getCreatedDate());
        //  hospitalisationListDs.setCreatedByUser(hospitalisation.getCreatedByUser());
        hospitalisationListDs.setNumeroHospitalisation(
                UtilString.createNumeroHospitalisation(hospitalisation.getNumeroHospitalisation())
        );
        if (hospitalisation.getCode() != null) {
            Patient patient = patientService.findByCode(hospitalisation.getCode());
            String nomPatient = patient.getPrenom() + ' ' + patient.getNom();
            hospitalisationListDs.setNomCompletPatient(nomPatient);
        }
        if (hospitalisation.getCreatedBy() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurById(hospitalisation.getCreatedBy());
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
        hospitalisationDs.setActif(hospitalisation.isActif());
        hospitalisationDs.setCreatedDate(hospitalisation.getCreatedDate());
        hospitalisationDs.setCreatedBy(hospitalisation.getCreatedBy());
        hospitalisationDs.setNumeroHospitalisation(
                UtilString.createNumeroHospitalisation(hospitalisation.getNumeroHospitalisation()));
        if (hospitalisation.getObservationClinique() != null)
            hospitalisationDs.setObservationCliniqueDs(observationCliniqueAssembler.assembleEntityToDs(
                    hospitalisation.getObservationClinique()));
        if (hospitalisation.getExamenComplementaire() != null)
            hospitalisationDs.setExamenComplementaireDs(examenComplementaireAssembler
                    .assembleEntityToDs(hospitalisation.getExamenComplementaire()));
        if (hospitalisation.getTraitementMedical() != null)
            hospitalisationDs.setTraitementMedicalDs(traitementMedicalAssembler
                    .assembleEntityToDs(hospitalisation.getTraitementMedical()));
        if (hospitalisation.getSynthese() != null)
            hospitalisationDs.setSyntheseDs(syntheseAssembler
                    .assembleEntityToDs(hospitalisation.getSynthese()));
        if (hospitalisation.getDiscussion() != null)
            hospitalisationDs.setDiscussionDs(discussionAssembler.assembleEntityToDs(hospitalisation.getDiscussion()));
        return hospitalisationDs;
    }

    public Hospitalisation assembleDsToEntity(HospitalisationDs hospitalisationDs) {
        Hospitalisation hospitalisation = new Hospitalisation();
        if (hospitalisationDs.getId() != null)
            hospitalisation.setId(hospitalisationDs.getId());
        hospitalisation.setCode(hospitalisationDs.getCode());
        hospitalisation.setMatricule(hospitalisationDs.getMatricule());
        hospitalisation.setActif(hospitalisationDs.isActif());
        hospitalisation.setCreatedDate(hospitalisationDs.getCreatedDate());
        hospitalisation.setCreatedBy(hospitalisationDs.getCreatedBy());
        if (hospitalisationDs.getObservationCliniqueDs() != null)
            hospitalisation.setObservationClinique(observationCliniqueAssembler.assembleObservationCliniqueFromDs(hospitalisationDs.getObservationCliniqueDs()));
        if (hospitalisationDs.getExamenComplementaireDs() != null)
            hospitalisation.setExamenComplementaire(examenComplementaireAssembler.assembleExamenComplementaireFromDs(hospitalisationDs.getExamenComplementaireDs()));
        if (hospitalisationDs.getTraitementMedicalDs() != null)
            hospitalisation.setTraitementMedical(traitementMedicalAssembler.assembleTraitementMedicalFromDs(hospitalisationDs.getTraitementMedicalDs()));
        if (hospitalisationDs.getSyntheseDs() != null)
            hospitalisation.setSynthese(syntheseAssembler.assembleSyntheseFromDs(hospitalisationDs.getSyntheseDs()));
        if (hospitalisationDs.getDiscussionDs() != null)
            hospitalisation.setDiscussion(discussionAssembler.assembleDiscussionFromDs(hospitalisationDs.getDiscussionDs()));
        return hospitalisation;
    }


    public Hospitalisation assembleUpdateHospitalisation(HospitalisationDs hospitalisationDs) {
        Hospitalisation hospitalisation = hospitalisationService.findById(hospitalisationDs.getId());

        if (hospitalisationDs.getObservationCliniqueDs() != null)
            hospitalisation.setObservationClinique(observationCliniqueAssembler.
                    assembleUpdateObservationCliniqueFromDs
                            (hospitalisation.getObservationClinique(), hospitalisationDs.getObservationCliniqueDs()));

        if (hospitalisationDs.getExamenComplementaireDs() != null)
            hospitalisation.setExamenComplementaire(examenComplementaireAssembler
                    .assembleUpdateExamenComplementaireFromDs(hospitalisation.getExamenComplementaire(), hospitalisationDs.getExamenComplementaireDs()));

        if (hospitalisationDs.getTraitementMedicalDs() != null)
            hospitalisation.setTraitementMedical(traitementMedicalAssembler
                    .assembleUpdateTraitementMedicalFromDs(hospitalisation.getTraitementMedical(), hospitalisationDs.getTraitementMedicalDs()));

        if (hospitalisationDs.getSyntheseDs() != null)
            hospitalisation.setSynthese(syntheseAssembler
                    .assembleUpdateSyntheseFromDs(hospitalisation.getSynthese(), hospitalisationDs.getSyntheseDs()));
        if (hospitalisationDs.getDiscussionDs() != null)
            hospitalisation.setDiscussion(discussionAssembler
                    .assembleUpdateDiscussionFromDs(hospitalisation.getDiscussion(), hospitalisationDs.getDiscussionDs()));
        return hospitalisation;
    }


    public HospitalisationDetailDs assembleHospitalisationDetailDsFromHospitalisation(Hospitalisation hospitalisation) {
        if (hospitalisation == null)
            return null;
        HospitalisationDetailDs hospitalisationDetailDs = new HospitalisationDetailDs();
        if (hospitalisation.getId() != null)
            hospitalisationDetailDs.setId(hospitalisation.getId());
        hospitalisationDetailDs.setActif(hospitalisation.isActif());
        hospitalisationDetailDs.setCreateDate(hospitalisation.getCreatedDate());
        hospitalisationDetailDs.setCreatedBy(hospitalisation.getCreatedBy());
        if (hospitalisation.getObservationClinique() != null)
            hospitalisationDetailDs.setObservationCliniqueDs(observationCliniqueAssembler.assembleEntityToDs(hospitalisation.getObservationClinique()));
        if (hospitalisation.getExamenComplementaire() != null)
            hospitalisationDetailDs.setExamenComplementaireDs(examenComplementaireAssembler.assembleEntityToDs(hospitalisation.getExamenComplementaire()));
        if (hospitalisation.getTraitementMedical() != null)
            hospitalisationDetailDs.setTraitementMedicalDs(traitementMedicalAssembler.assembleEntityToDs(hospitalisation.getTraitementMedical()));
        if (hospitalisation.getSynthese() != null)
            hospitalisationDetailDs.setSyntheseDs(syntheseAssembler.assembleEntityToDs(hospitalisation.getSynthese()));
        if (hospitalisation.getDiscussion() != null)
            hospitalisationDetailDs.setDiscussionDs(discussionAssembler.assembleEntityToDs(hospitalisation.getDiscussion()));
        hospitalisationDetailDs.setNumeroHospitalisation(
                UtilString.createNumeroHospitalisation(hospitalisation.getNumeroHospitalisation())
        );
        if (hospitalisation.getCode() != null) {
            Patient patient = patientService.findByCode(hospitalisation.getCode());
            PatientDetailDs patientDetailDs = patientAssembler.assemblePatientDetails(patient);
            hospitalisationDetailDs.setPatientDetailDs(patientDetailDs);
        }
        if (hospitalisation.getCreatedBy() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurById(hospitalisation.getCreatedBy());
            UtilisateurDs utilisateurDs = utilisateurAssembler.assembleUtilisateurDsFromEntity(utilisateur);
            hospitalisationDetailDs.setUtilisateurDs(utilisateurDs);
            hospitalisationDetailDs.setNomCompletMedecin(utilisateur.getPrenom() + ' ' + utilisateur.getNom());
        }
        return hospitalisationDetailDs;
    }


}
