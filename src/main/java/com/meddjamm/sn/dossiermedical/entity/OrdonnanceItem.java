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
@Table(name = "ordonnance_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdonnanceItem extends AbstractAuditableEntity implements Serializable {

    @Column(name = "medicament_uid")
    private Long code;

    private String psologie;

    private String nbrePrise;

}