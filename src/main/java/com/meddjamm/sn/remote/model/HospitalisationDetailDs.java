package com.meddjamm.sn.remote.model;

import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalisationDetailDs {

    private Long id;

    private PatientDetailDs patientDetailDs;

    private MedecinDetailDs medecinDetailDs;

    private String resume;

    private List<DocumentDs> documentDs;

    private Date createdDate;

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
