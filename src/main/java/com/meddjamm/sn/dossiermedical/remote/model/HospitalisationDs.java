package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalisationDs {

    private Long id;

    private String numeroHospitalisation;

    private String code;

    private Long patientId;

    private String nomCompletPatient;

    private String matricule;

    private Long createdBy;

    private Date createdDate;

    private ObservationCliniqueDs observationCliniqueDs;

    private ExamenComplementaireDs examenComplementaireDs;

    private TraitementMedicalDs traitementMedicalDs;

    private DiscussionDs discussionDs;

    private SyntheseDs syntheseDs;

    private String resume;

    private int est_Transfer;

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
