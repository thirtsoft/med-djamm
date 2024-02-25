package com.meddjamm.sn.entity;
import jakarta.persistence.*;
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
public class AntecedentMedicaux implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

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
