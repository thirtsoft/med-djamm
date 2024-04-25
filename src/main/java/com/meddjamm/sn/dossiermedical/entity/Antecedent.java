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

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "antecedents_medicaux_par_antecedent",
//            joinColumns = @JoinColumn(name = "id"))
//    @Column(name = "antecedents_medicaux")
//    private Set<String> antecedentsMedicaux;

    @Column(name = "antecedents_chirurgicaux", columnDefinition = "TEXT")
    private String antecedentsChirurgicaux;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "antecedents_chirurgicaux_par_antecedent",
//            joinColumns = @JoinColumn(name = "id"))
//    @Column(name = "antecedents_chirurgicaux")
//    private Set<String> antecedentsChirurgicaux;

    @Column(name = "antecedents_gynecologies", columnDefinition = "TEXT")
    private String antecedentsGynecologiques;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "antecedents_gynecologies_par_antecedent",
//            joinColumns = @JoinColumn(name = "id"))
//    @Column(name = "antecedents_gynecologies")
//    private Set<String> antecedentsGynecologiques;

    @Column(name = "antecedents_familials_ascendant", columnDefinition = "TEXT")
    private String antecedentsFamilialsAscendant;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "antecedents_familials_ascendant_par_antecedent",
//            joinColumns = @JoinColumn(name = "id"))
//    @Column(name = "antecedents_familials_ascendant")
//    private Set<String> antecedentsFamilialsAscendant;

    @Column(name = "antecedents_familials_collateral", columnDefinition = "TEXT")
    private String antecedentsFamilialsCollateral;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "antecedents_familials_collateral_par_antecedent",
//            joinColumns = @JoinColumn(name = "id"))
//    @Column(name = "antecedents_familials_collateral")
//    private Set<String> antecedentsFamilialsCollateral;

    @Column(name = "antecedents_familials_descendant", columnDefinition = "TEXT")
    private String antecedentsFamilialsDescendant;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "antecedents_familials_descendant_par_antecedent",
//            joinColumns = @JoinColumn(name = "id"))
//    @Column(name = "antecedents_familials_descendant")
//    private Set<String> antecedentsFamilialsDescendant;

    @Column(name = "modes_de_vie", columnDefinition = "TEXT")
    private String modeVies;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "mode_de_vie_par_antecedent",
//            joinColumns = @JoinColumn(name = "id"))
//    @Column(name = "modes_de_vie")
//    private Set<String> modeVies;

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
