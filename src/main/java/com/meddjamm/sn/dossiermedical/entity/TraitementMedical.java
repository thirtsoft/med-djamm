package com.meddjamm.sn.dossiermedical.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "traitement_medical")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraitementMedical extends AbstractAuditableEntity implements Serializable {

    @Column(columnDefinition = "TEXT")
    private String protocole;

    private String protocoleFileName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "traitement_medical_item_par_traitement", joinColumns =
    @JoinColumn(name = "traitement_uid"),
            inverseJoinColumns = @JoinColumn(name = "traitement_medical_item_uid"))
    private Set<TraitementMedicalItem> traitementMedicalItems;

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
