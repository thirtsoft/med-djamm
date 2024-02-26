package com.meddjamm.sn.remote.model;

import com.meddjamm.sn.dossiermedical.remote.model.OrdonnanceItemDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdonnanceDs {

    private Long id;

    private String code;

    private String indexPatient;

    private String matricule;

    private Long passageId;

    private String psologie;

    private String nbrePrise;

    private List<OrdonnanceItemDs> ordonnanceItemDs;

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
