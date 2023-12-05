package com.meddjamm.sn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "antecedent_medicaux")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AntecedentMedicaux implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "maladie_antecedent_par_antecedent_medicauxr", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "maladies_antecedent")
    private Set<String> maladiesAntecedent;
}
