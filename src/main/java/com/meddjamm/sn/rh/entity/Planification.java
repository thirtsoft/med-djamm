package com.meddjamm.sn.rh.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "planification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Planification extends AbstractAuditableEntity implements Serializable {

    private String libelle;

    @Column(name = "agent_medical_uid")
    private Long agentId;

    @Column(name = "date_debut_service")
    private Date dateDebut;

    @Column(name = "date_fin_service")
    private Date dateFin;

    private int isCreatedBy;

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
