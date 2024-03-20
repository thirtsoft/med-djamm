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
@Table(name = "antecedent_medicaux")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AntecedentMedicaux extends AbstractAuditableEntity implements Serializable {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "maladie_antecedent_par_antecedent_medicaux",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "maladies_antecedent")
    private Set<String> maladiesAntecedent;

    private Date createDate;

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
