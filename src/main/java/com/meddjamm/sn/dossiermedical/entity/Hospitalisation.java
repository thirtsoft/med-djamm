package com.meddjamm.sn.dossiermedical.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "hospitalisation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hospitalisation extends AbstractAuditableEntity implements Serializable {

    @Column(name = "numero_hospisatisation", nullable = true, unique = true)
    private int numeroHospitalisation;

    @Column(name = "patient_uid")
    private String code;

    @Column(name = "medecin_uid")
    private String matricule;

    private String resume;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<ObservationClinique> observationCliniqueList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ExamenComplementaire> examenComplementaires;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TraitementMedical> traitementMedicals;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Discussion> discussions;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Synthese> syntheseList;

    private int est_Transfer;

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
