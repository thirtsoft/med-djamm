package com.meddjamm.sn.remote.model;

import com.meddjamm.sn.dossiermedical.remote.model.OrdonnanceItemDs;
import com.meddjamm.sn.dossiermedical.remote.model.PatientDetailDs;
import com.meddjamm.sn.rh.remote.model.MedicamentDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdonnanceDetailDs {

    private Long id;

    private MedicamentDs medicamentDs;

    private PatientDetailDs patientDetailDs;

    private MedecinDetailDs medecinDetailDs;

    private List<OrdonnanceItemDs> ordonnanceItemDs;

    private String psologie;

    private String nbrePrise;

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
