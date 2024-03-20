package com.meddjamm.sn.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import com.meddjamm.sn.dossiermedical.entity.ExamenComplementaire;
import com.meddjamm.sn.dossiermedical.entity.ObservationClinique;
import com.meddjamm.sn.dossiermedical.entity.TraitementMedical;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dossier_medical_informatise")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DossierMedicalInformatise extends AbstractAuditableEntity implements Serializable {

    @Column(name = "numero_dossier_medical", nullable = true, unique = true)
    private int numeroDossierMedical;

    @Column(name = "patient_uid")
    private String code;

    @Column(name = "medecin_uid")
    private String matricule;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<ObservationClinique> observationCliniqueList;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<ExamenComplementaire> examenComplementaires;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<TraitementMedical> traitementMedicals;

    private Long createdBy;

    private Date createDate;

    private int estTransfere;

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