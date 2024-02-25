package com.meddjamm.sn.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntecedentFamilialDs implements Serializable {

    private Long id;

    /*
    private String antecedentAscendant;

    private String antecedentCollateral;

    private String antecedentDescendant;

    private String indexPatient;
    */

    private List<String> familialsAntecedentAscendant;

    private List<String> familialsAntecedentCollateral;

    private List<String> familialsAntecedentDescendant;

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
