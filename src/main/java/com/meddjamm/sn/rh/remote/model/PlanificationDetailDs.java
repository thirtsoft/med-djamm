package com.meddjamm.sn.rh.remote.model;

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

    private Long agentMedical;

 //   private AgentMedicalDs agentMedicalDs;

    private String nomCompletAgent;

    private Date dateService;

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
