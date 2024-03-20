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
@Table(name = "antecedent-chirurgie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AntecedentChirurgie extends AbstractAuditableEntity implements Serializable {

    /*
    private String libelle;

    @Column(name = "patient_id")
    private String indexPatient;*/

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "chirurgie_antecedent_par_antecedent_chirurgical",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "chirurgies_antecedent")
    private Set<String> chirurgiesAntecedent;

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
