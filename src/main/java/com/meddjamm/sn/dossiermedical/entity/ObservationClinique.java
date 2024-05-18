package com.meddjamm.sn.dossiermedical.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "observation_clinique")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObservationClinique extends AbstractAuditableEntity implements Serializable {


    @Column(name = "motifs_hospitalisation", columnDefinition = "TEXT")
    private String motifsHospitalisation;

    @Column(name = "histoire_maladie", columnDefinition = "TEXT")
    private String histoireMaladie;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Antecedent antecedent;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private ExamenPhysique examenPhysique;

    private Date createdDate;

    private int actif;

    public boolean isActif() {
        if (actif == 1) return true;
        else return false;
    }

    public void setActif(boolean actif) {
        if (actif == true) this.actif = 1;
        else this.actif = 0;
    }

}
