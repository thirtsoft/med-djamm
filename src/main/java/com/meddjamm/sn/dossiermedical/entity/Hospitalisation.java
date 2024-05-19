package com.meddjamm.sn.dossiermedical.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "hospitalisation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospitalisation extends AbstractAuditableEntity implements Serializable {

    @Column(name = "numero_hospisatisation", nullable = true, unique = true)
    private int numeroHospitalisation;

    @Column(name = "patient_uid")
    private String code;

    @Column(name = "medecin_uid")
    private String matricule;

    @OneToOne(cascade = {CascadeType.ALL})
    private ObservationClinique observationClinique;

    @OneToOne(cascade = {CascadeType.ALL})
    private ExamenComplementaire examenComplementaire;

    @OneToOne(cascade = {CascadeType.ALL})
    private TraitementMedical traitementMedical;

    @OneToOne(cascade = {CascadeType.ALL})
    private Discussion discussion;

    @OneToOne(cascade = {CascadeType.ALL})
    private Synthese synthese;

    private Long createdBy;

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
