package com.meddjamm.sn.remote.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDs {

    private Long id;

    private String codeUtilisateur;

    private String motdepasse;

    private String motdepasseprecedent;

    private int est_valide;

    private int mdpamodifier;

    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    private Date dateCreation;

    private Date dateModPass;

    private int resteValidite;

    private Long profilId;

    private String activation;

    private Long createdBy;

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

    public boolean isMdpamodifier() {
        if (mdpamodifier == 1)
            return true;
        else
            return false;
    }

    public void setMdpamodifier(boolean mdpamodifier) {
        if (mdpamodifier == true)
            this.mdpamodifier = 1;
        else
            this.mdpamodifier = 0;
    }
}