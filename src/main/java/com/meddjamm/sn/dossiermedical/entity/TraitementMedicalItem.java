package com.meddjamm.sn.dossiermedical.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "traitement_medical_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraitementMedicalItem extends AbstractAuditableEntity implements Serializable {


    @Column(name = "medicament_uid")
    private Long medicamendId;

    private String psologie;

    private String nbrePrise;

    private String administrePar;

    private int est_administre;
}
