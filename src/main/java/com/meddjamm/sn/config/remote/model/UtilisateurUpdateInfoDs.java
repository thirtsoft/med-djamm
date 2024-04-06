package com.meddjamm.sn.config.remote.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilisateurUpdateInfoDs {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String civilite;
    private String sexe;
    private String telephone;
    private Date dateRecrutement;
    private String fonction;
    private String adresse;
    private String education;
    private String experience;
    private String speciality;

    private int est_valide;

    public boolean isEst_valide() {
        if (est_valide == 1)
            return true;
        else
            return false;
    }

    public void setEst_valide(boolean est_valide) {
        if (est_valide == true)
            this.est_valide = 1;
        else
            this.est_valide = 0;
    }

}