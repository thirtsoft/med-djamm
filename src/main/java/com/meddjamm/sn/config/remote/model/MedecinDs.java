package com.meddjamm.sn.config.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedecinDs {

    private Long id;

    private String matricule;

    private String civilite;

    private String nom;
    private String prenom;

    private String sexe;

    private String telephone;

    private Long speciality;

    private String email;

    private Date dateRecrutement;

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
