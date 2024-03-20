package com.meddjamm.sn.dossiermedical.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "antecedent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Antecedent extends AbstractAuditableEntity implements Serializable {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "antecedents_medicaux_par_antecedent",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "antecedents_medicaux")
    private Set<String> antecedentsMedicaux;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "antecedents_chirurgicaux_par_antecedent",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "antecedents_chirurgicaux")
    private Set<String> antecedentsChirurgicaux;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "antecedents_gynecologies_par_antecedent",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "antecedents_gynecologies")
    private Set<String> antecedentsGynecologiques;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "antecedents_familials_ascendant_par_antecedent",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "antecedents_familials_ascendant")
    private Set<String> antecedentsFamilialsAscendant;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "antecedents_familials_collateral_par_antecedent",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "antecedents_familials_collateral")
    private Set<String> antecedentsFamilialsCollateral;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "antecedents_familials_descendant_par_antecedent",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "antecedents_familials_descendant")
    private Set<String> antecedentsFamilialsDescendant;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "mode_de_vie_par_antecedent",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "modes_de_vie")
    private Set<String> modeVies;

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
