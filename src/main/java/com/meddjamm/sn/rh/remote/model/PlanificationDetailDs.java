package com.meddjamm.sn.rh.remote.model;

import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanificationDetailDs {

    private Long id;

    private String libelle;

    private Long agentId;

    private String nomCompletAgent;

    private UtilisateurDs utilisateurDs;

    private int isCreatedBy;

    private Date dateDebut;

    private Date dateFin;

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
