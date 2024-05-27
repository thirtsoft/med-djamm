package com.meddjamm.sn.rh.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "codage_patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodagePatient extends AbstractAuditableEntity implements Serializable {

    @Column(name = "patient_uid")
    private Long patientId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "codage_epidemiologique_par_patient", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "codage_epidemiologique")
    private Set<String> epidemiologiques;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "codage_clinique_par_patient", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "codage_clinique")
    private Set<String> cliniques;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "codage_examen_complementaire_par_patient", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "codage_examen_complementaire")
    private Set<String> examenComplementaires;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "codage_traitement_par_patient", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "codage_traitement")
    private Set<String> traitements;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "codage_synthese_par_patient", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "codage_synthese")
    private Set<String> syntheses;

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
