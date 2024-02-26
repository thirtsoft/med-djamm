package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntecedentDs {

    private Long id;

    private List<String> antecedentsMedicaux;

    private List<String> antecedentsChirurgicaux;

    private List<String> antecedentsGynecologiques;

    private List<String> antecedentsFamilialsAscendant;

    private List<String> antecedentsFamilialsCollateral;

    private List<String> antecedentsFamilialsDescendant;

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
