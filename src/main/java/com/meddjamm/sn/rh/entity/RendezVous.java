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
@Table(name = "rendez_vous")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezVous extends AbstractAuditableEntity implements Serializable {

    private String libelle;

    @Column(name = "patient_uid")
    private Long patientId;

    @Column(name = "medecin_uid")
    private Long medecinId;

    private Long createdBy;

    @Column(name = "date_rendez_vous")
    private Date dateRendezVous;

    private String heure;

    private Date createDate;

    private int etat;

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
