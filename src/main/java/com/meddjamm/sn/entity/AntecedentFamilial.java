package com.meddjamm.sn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "antecedent-familial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntecedentFamilial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

  //  private String antecedentAscendant;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "familial_antecedent_ascendant_par_antecedent_familial",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "familials_antecedent_ascendant")
    private Set<String> familialsAntecedentAscendant;

 //   private String antecedentCollateral;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "familial_antecedent_collateral_par_antecedent_familial",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "familials_antecedent_collateral")
    private Set<String> familialsAntecedentCollateral;

//    private String antecedentDescendant;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "familial_antecedent_descendant_par_antecedent_familial",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "familials_antecedent_descendant")
    private Set<String> familialsAntecedentDescendant;

    /*
    @Column(name = "patient_id")
    private String indexPatient;*/

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
