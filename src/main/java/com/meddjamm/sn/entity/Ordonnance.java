package com.meddjamm.sn.entity;

import com.meddjamm.sn.dossiermedical.entity.OrdonnanceItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "ordonnance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ordonnance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "medicament_uid")
    private String code;

    @Column(name = "patient_uid")
    private String indexPatient;

    @Column(name = "medecin_uid")
    private String matricule;

    private String psologie;

    private String nbrePrise;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passage_patient_uid")
    private PassagePatient passagePatient;*/

    @JoinColumn(name = "passage_patient_uid")
    private Long passagePatientId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ordonnance_item_par_ordonnance", joinColumns =
    @JoinColumn(name = "ordonnance_uid"),
            inverseJoinColumns = @JoinColumn(name = "ordonnance_item_uid"))
    private Set<OrdonnanceItem> ordonnanceItems;

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
