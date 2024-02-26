package com.meddjamm.sn.rh.remote.model;

import com.meddjamm.sn.remote.model.UtilisateurDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentMedicalDs {

    private Long id;

    private String codeAgent;

    private String civilite;

    private String sexe;

    private String telephone;

    private String portable;

    private String fonction;

    private Date dateRecrutement;

    private int actif;

    private Long speciality;

    private UtilisateurDs utilisateurDs;

    private int affecte;

    public boolean isAffecte() {
        if (affecte == 1)
            return true;
        else
            return false;
    }

    public void setAffecte(boolean affecte) {
        if (affecte == true)
            this.affecte = 1;
        else
            this.affecte = 0;
    }

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
