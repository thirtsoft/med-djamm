package com.meddjamm.sn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "diagnostic_associe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticAssocie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "libelles_diagnostic_par_diagnostic_associe", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "libelles_diagnostic")
    private Set<String> libellesDiagnostic;

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
