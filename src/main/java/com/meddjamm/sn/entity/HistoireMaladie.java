package com.meddjamm.sn.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "histoire_maladie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoireMaladie extends AbstractAuditableEntity implements Serializable {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "atteinte_precedent_hisotire_maladie", joinColumns = @JoinColumn(name = "hisotire_maladie_uid"))
    @Column(name = "atteinte_precedent_uid")
    private Set<Long> atteintePrecedents;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "atteinte_actuel_hisotire_maladie", joinColumns = @JoinColumn(name = "hisotire_maladie_uid"))
    @Column(name = "atteinte_actuel_uid")
    private Set<Long> atteinteActuels;

    private int age;

    private String circonstance;

    private Date dateDiagnostic;

    private String infoComplementaire;

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