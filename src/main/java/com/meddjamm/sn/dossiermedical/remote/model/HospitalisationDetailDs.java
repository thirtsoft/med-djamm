package com.meddjamm.sn.dossiermedical.remote.model;

import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalisationDetailDs {

    private Long id;

    private String numeroHospitalisation;

    private PatientDetailDs patientDetailDs;

    private UtilisateurDs utilisateurDs;

    private List<ObservationCliniqueDs> observationCliniqueDsList;

    private List<ExamenComplementaireDs> examenComplementaireDsList;

    private List<TraitementMedicalDs> traitementMedicalDsList;

    private List<DiscussionDs> discussionDsList;

    private List<SyntheseDs> syntheseDsList;

    private String resume;

    private int est_Transfer;

    private LocalDateTime createDate;

    private int actif;

    public boolean isActif() {
        if (actif == 1) return true;
        else return false;
    }

    public void setActif(boolean actif) {
        if (actif == true) this.actif = 1;
        else this.actif = 0;
    }

}
