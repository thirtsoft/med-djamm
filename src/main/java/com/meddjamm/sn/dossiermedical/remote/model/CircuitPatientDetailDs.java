package com.meddjamm.sn.dossiermedical.remote.model;

import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import com.meddjamm.sn.remote.model.MedecinDetailDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CircuitPatientDetailDs {

    private Long id;

    private String numeroCircuit;

    private String code;

    private PatientDetailDs patientDetailDs;

    private String matricule;

    private MedecinDetailDs medecinDetailDs;

    private UtilisateurDs utilisateurDetailDs;

    private String nomCompletAgent;

    private String type;

    private int etat;

    private Long createdBy;
    private String createdByUser;

    private List<ObservationCliniqueDs> observationCliniqueDs;

    private List<ExamenComplementaireDs> examenComplementaireDs;

    private List<TraitementMedicalDs> traitementMedicalDs;

    private List<ConsultationDs> consultationDs;

    private List<OrdonnanceDs> ordonnanceDs;

    private List<AvisSpecialisteDs> avisSpecialisteDs;

    private List<SyntheseDs> syntheseDs;

    private List<ExamenBiologiqueDs> examenBiologiqueDs;

    private List<DiscussionDs> discussionDs;

    private Date createDate;

    private int actif;

    public boolean isActif() {
        if (actif == 1)
            return true;
        else
            return false;
    }

    public void setActif(boolean actif) {
        if (actif == true)
            this.actif = 1;
        else
            this.actif = 0;
    }
}
