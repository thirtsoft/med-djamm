package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntecedentDs {

    private Long id;

    private String antecedentsMedicaux;

    private String antecedentsChirurgicaux;

    private String antecedentsGynecologiques;

    private String antecedentsFamilialsAscendant;

    private String antecedentsFamilialsCollateral;

    private String antecedentsFamilialsDescendant;

    private String modeVies;

//    private List<String> antecedentsMedicaux;
//
//    private List<String> antecedentsChirurgicaux;
//
//    private List<String> antecedentsGynecologiques;
//
//    private List<String> antecedentsFamilialsAscendant;
//
//    private List<String> antecedentsFamilialsCollateral;
//
//    private List<String> antecedentsFamilialsDescendant;
//
//    private List<String> modeVies;

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
