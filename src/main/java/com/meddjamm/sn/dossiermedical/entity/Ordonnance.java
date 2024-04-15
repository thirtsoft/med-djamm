package com.meddjamm.sn.dossiermedical.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.DETACH;
import static jakarta.persistence.CascadeType.MERGE;

@EqualsAndHashCode
@Entity
@Table(name = "ordonnance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ordonnance extends AbstractAuditableEntity implements Serializable {

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            ALL, MERGE, DETACH
    })
    @JoinTable(name = "ordonnance_item_par_ordonnance", joinColumns =
    @JoinColumn(name = "ordonnance_uid"),
            inverseJoinColumns = @JoinColumn(name = "ordonnance_item_uid"))
    private Set<OrdonnanceItem> ordonnanceItems = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circuit_patient_uid")
    private CircuitPatient circuitPatient;

    @JoinColumn(name = "circuit_patient_id")
    private Long circuitPatientId;

    private Date createdDate;

    private Long createdBy;

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
