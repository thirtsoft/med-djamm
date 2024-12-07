package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalisationListDs {

    private Long id;

    private String numeroHospitalisation;

    private String nomCompletPatient;

    private String nomCompletMedecin;

    private String resume;

    private int est_Transfer;

    private Date createDate;

    private String createdByUser;

    private int actif;

    public boolean isActif() {
        return actif == 1;
    }

    public void setActif(boolean actif) {
        if (actif)
            this.actif = 1;
        else
            this.actif = 0;
    }

}
