package com.meddjamm.sn.dossiermedical.remote.model;

import com.meddjamm.sn.remote.model.DocumentDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationDetailDs {

    private Long id;

    private String resume;

    private PatientDetailDs patientDetailDs;

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
