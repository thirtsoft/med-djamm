package com.meddjamm.sn.dossiermedical.entity;

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
@Table(name = "antecedent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Antecedent extends AbstractAuditableEntity implements Serializable {

    @Column(name = "antecedents_medicaux", columnDefinition = "TEXT")
    private String antecedentsMedicaux;

    @Column(name = "antecedents_chirurgicaux", columnDefinition = "TEXT")
    private String antecedentsChirurgicaux;

    @Column(name = "antecedents_gynecologies", columnDefinition = "TEXT")
    private String antecedentsGynecologiques;

    @Column(name = "antecedents_familials_ascendant", columnDefinition = "TEXT")
    private String antecedentsFamilialsAscendant;

    @Column(name = "antecedents_familials_collateral", columnDefinition = "TEXT")
    private String antecedentsFamilialsCollateral;

    @Column(name = "antecedents_familials_descendant", columnDefinition = "TEXT")
    private String antecedentsFamilialsDescendant;

    @Column(name = "modes_de_vie", columnDefinition = "TEXT")
    private String modeVies;

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
